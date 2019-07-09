# ------------------------------
# AUTHOR: MERT ÖZTÜRK
# Contact: mert1ozturk@gmail.com
# ------------------------------

import json
import torch
import argparse
import numpy as np
from torch import nn, optim
from torchvision import datasets, transforms, models
import torch.nn.functional as F
from PIL import Image

# To load and preprocess the data
def data_load(data_dir):
    training_transforms = transforms.Compose([transforms.RandomRotation(30),
                                       transforms.RandomResizedCrop(224),
                                       transforms.RandomHorizontalFlip(),
                                       transforms.ToTensor(),
                                       transforms.Normalize([0.485, 0.456, 0.406],
                                                            [0.229, 0.224, 0.225])])

    validation_transforms = transforms.Compose([transforms.Resize(256),
                                          transforms.CenterCrop(224),
                                          transforms.ToTensor(),
                                          transforms.Normalize([0.485, 0.456, 0.406],
                                                               [0.229, 0.224, 0.225])])

    test_transforms = transforms.Compose([transforms.Resize(256),
                                          transforms.CenterCrop(224),
                                          transforms.ToTensor(),
                                          transforms.Normalize([0.485, 0.456, 0.406],
                                                               [0.229, 0.224, 0.225])])

    training_data = datasets.ImageFolder(data_dir + "/train", transform=training_transforms)
    validation_data = datasets.ImageFolder(data_dir + "/valid", transform=validation_transforms)
    test_data = datasets.ImageFolder(data_dir + "/test", transform=test_transforms)

    # We are going to leave shuffle as True so that the training model won't be affected by the order of the images
    training_loader = torch.utils.data.DataLoader(training_data, batch_size=64, shuffle=True)
    validation_loader = torch.utils.data.DataLoader(validation_data, batch_size=64)
    test_loader = torch.utils.data.DataLoader(test_data, batch_size=64)

    return training_loader, validation_loader, test_loader, training_data, validation_data, test_data


def image_preprocess(image_dir):
    """
    Takes an image as an input and preprocesses it so that it is
    suitable for a PyTorch model.

    In order: scale, crop, normalize, turn into a NumPy array, return.
    """

    # Converting the image into PIL format so that it is suitable for PyTorch
    image_PIL = Image.open(image_dir)

    transform = transforms.Compose([transforms.Resize(256),
                                    transforms.CenterCrop(224),
                                    transforms.ToTensor(),
                                    transforms.Normalize([0.485, 0.456, 0.406],
                                                         [0.229, 0.224, 0.225])])

    image_transformed = transform(image_PIL)

    image_transformed_array = np.array(image_transformed)

    image_tensor = torch.from_numpy(image_transformed_array).type(torch.FloatTensor)

    image_final = image_tensor.unsqueeze_(0)

    return image_final


def model_train(model, epoch, optimizer, criterion, gpu, training_loader, validation_loader):
    # Default values
    epoch=5
    steps=3
    print_every=10

    if gpu==True:
        model.to("cuda")
    else:
        pass

    for e in range(epoch):
        running_loss = 0

        for ii, (inputs, labels) in enumerate(training_loader):
            steps += 1

            if gpu==True:
                inputs, labels = inputs.to('cuda'), labels.to('cuda')
            else:
                pass

            # We need to zero the gradients
            optimizer.zero_grad()

            outputs = model.forward(inputs)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()

            running_loss += loss.item()

            if steps % print_every == 0:
                model.eval()

                with torch.no_grad():
                    validation_loss, accuracy = get_validation(model, validation_loader, criterion, gpu)

                validation_loss = validation_loss / len(validation_loader)

                print("Epoch: {}/{}    ".format(e+1, epochs),
                      "Training Loss: {:.3f}%    ".format(running_loss/print_every),
                      "Validation Lost: {:.3f}%    ".format(100*validation_loss/len(validation_loader)),
                      "Accuracy: {:.3f}%    ".format(100*accuracy/len(validation_loader)))

                running_loss = 0

                # Setting the model back to training since we are done with evaluation
                model.train()

    return model, optimizer


def model_test(model, test_loader, gpu):
    correct=0
    total=0

    if gpu==True:
        model.to("cuda")
    else:
        pass

    with torch.no_grad():
        for ii, (images, labels) in enumerate(test_loader):

            if gpu==True:
                images, labels = images.to("cuda"), labels.to("cuda")
            else:
                pass

            outputs = model(images)
            nevermind, predicted = torch.max(outputs.data, 1)

            correct += (predicted == labels).sum().item()
            total += labels.size(0)

    print("Accuracy of the network on the test dataset is: {:.3f}%".format(100 * correct / total))


def model_save(model, epoch, optimizer, training_data, save_dir):
    # Saving: feature weights, new classifier, index-to-class mapping, optimiser state, and No. of epochs
    checkpoint = {"state_dict": model.state_dict(),
                  "num_epochs": epoch,
                  "classifier": model.classifier,
                  "class_to_idx": training_data.class_to_idx,
                  "opt_state": optimizer.state_dict
                  }

    return torch.save(checkpoint, save_dir)


def checkpoint_load(model, save_dir, gpu):

    if gpu==True:
        checkpoint = torch.load(save_dir)
    else:
        # Refer: https://discuss.pytorch.org/t/on-a-cpu-device-how-to-load-checkpoint-saved-on-gpu-device/349/2
        checkpoint = torch.load(save_dir, map_location=lambda storage, loc: storage)

    model.classifier = checkpoint['classifier']
    model.load_state_dict(checkpoint['state_dict'])
    model.class_to_idx = checkpoint['class_to_idx']

    return model


def get_validation(model, validation_loader, criterion, gpu):
    validation_loss = 0
    accuracy = 0

    if gpu==True:
        model.to('cuda')
    else:
        pass

    for ii, (images, labels) in enumerate(validation_loader):

        if gpu_mode==True:
            images, labels = images.to('cuda'), labels.to('cuda')
        else:
            pass

        output = model.forward(images)
        validation_loss += criterion(output, labels).item()
        probabilities = torch.exp(output)
        equality = (labels.data == probabilities.max(dim=1)[1])
        accuracy += equality.type(torch.FloatTensor).mean()

    return valvalidation_lossid_loss, accuracy


def classifier_create(model, n_input_units, n_hidden_units, dropout_rate):

    # No need
    for p in model.parameters():
        p.requires_grad = False

    from collections import OrderedDict
    classifier = nn.Sequential(OrderedDict([
                              ('fc1', nn.Linear(n_input_units, n_hidden_units)),
                              ('relu', nn.ReLU()),
                              ('dropout', nn.Dropout(dropout_rate)),
                              ('fc2', nn.Linear(n_hidden_units, 102)),
                              ('output', nn.LogSoftmax(dim=1))
                              ]))

    model.classifier = classifier

    return model


def predict(image, model, topk, gpu):

    model.eval()

    if gpu==True:
        model.to("cuda")
    else:
        model.cpu()

    with torch.no_grad():
        output = model.forward(image)

    probabilities = torch.exp(output)
    probabilities_top = probabilities.topk(topk)[0]
    indexes_top = probabilities.topk(topk)[1]

    probabilities_top_list = np.array(probabilities_top)[0]
    indexes_top_list = np.array(indexes_top[0])

    class_to_idx = model.class_to_idx

    indexes_to_classes = {y: x for x, y in class_to_idx.items()}

    classes_top_list = []
    for item in indexes_top_list:
        classes_top_list += [indexes_to_classes[item]]

    return probabilities_top_list, classes_top_list
