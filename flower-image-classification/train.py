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

# Custom methods
from utilities import model_train, model_test, model_save
from utilities import checkpoint_load, data_load, image_preprocess
from utilities import classifier_create, get_validation, predict

parser = argparse.ArgumentParser()

parser.add_argument("--dir", type=str, default="flower_data", help="Type in the directory of your dataset.")
parser.add_argument("--model", type=str, default="vgg19", help="Type in the NN model you want to train with. Available models are: vgg19, alexnet, densenet121.")
parser.add_argument("--lr", type=float, default=0.001, help="Type in the learning rate you want.")
parser.add_argument("--hidden", type=int, default=500, help="Type in the number of hidden units you want.")
parser.add_argument("--epoch", type=int, default=5, help="Type in the number of epoch you want.")
parser.add_argument("--dropout", type=float, default=0.5, help="Type in the value of dropouts that will be used in the training set.")
parser.add_argument("--gpu", default=False, action="store_true", help="Choose between GPU and CPU. Note: GPU is highly advised.")
parser.add_argument("--savedir", type=str, default="checkpoint.pth", help="Type in the location you want the checkpoints to be saved at.")

user_input = parser.parse_args()

dir = user_input.dir
model_architecture = user_input.model
learning_rate = user_input.lr
n_hidden_units = user_input.hidden
epoch = user_input.epoch
dropout_rate = user_input.dropout
gpu = user_input.gpu
save_dir = user_input.savedir

# We are handling the data loading and preprocessing part
training_loader, validation_loader, test_loader, training_data, validation_data, test_data = data_load(dir)

if model_architecture=="vgg19":
    model = models.vgg19(pretrained=True)
    n_input_units = model.classifier[0].in_features
elif model_architecture=="alexnet":
    model = models.alexnet(pretrained=True)
    n_input_units = model.classifier[1].in_features
elif model_architecture=="densenet121":
    model = models.densenet121(pretrained=True)
    n_input_units = model.classifier.in_features
else:
    print("Oops. This program doesn't support {}. Please choose between vgg19, alexnet, densenet121".format(model_architecture))


classifier_create(model, n_input_units, n_hidden_units, dropout_rate)

# Since we are using Softmax
criterion = nn.NLLLoss()
optimizer = optim.Adam(model.classifier.parameters(), learning_rate)

# Train the model
model, optimizer = model_train(model, epoch, optimizer, criterion, gpu, training_loader, validation_loader)

# Test the model
model_test(model, test_loader, gpu)

# Save the model for later use
trained_model = model_save(model, epoch, optimizer, training_data, save_dir)
