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

parser.add_argument("--checkpoint", type=str, default="checkpoint.pth", help="Type in the directory of the pretrained model you will use to make predictions.")
parser.add_argument("--savedir", type=str, default="checkpoints", help="Type in the location you want the checkpoints to be saved at.")
parser.add_argument("--categories", type=str, default="cat_to_name.json", help="The file that contains name of the categories.")
parser.add_argument("--gpu", type=bool, default=True, help="Choose between GPU and CPU. Note: GPU is highly advised.")
parser.add_argument("--image", type=str, default="test_image.jpg", help="Type in the folder that contains the images that you would like to make predictions on.")
parser.add_argument("--topk", type=int, default=5, help="Type in the number of top K classes to be returned.")

user_input = parser.parse_args()

gpu = user_input.gpu
topk = user_input.topk
prediction_image_dir = user_input.image
category_names = user_input.categories
save_dir = user_input.savedir
model_architecture = user_input.checkpoint

with open(category_names, "r") as f:
    cat_to_name = json.load(f)

model = getattr(models, model_architecture)(pretrained=True)

model_loaded = checkpoint_load(model, save_dir, gpu)

image_processed = image_preprocess(prediction_image_dir)

if gpu==True:
    image_processed = image_processed.to("cuda")
else:
    pass

# Make predictions
probabilities, classes = predict(image_processed, model, topk, gpu)
print(probabilities)
print(classes)

classes_names = []
for i in classes:
    classes_names += [cat_to_name[i]]

print("The flower in the photo you uploaded is most likely {} with the probability of {:.2f}%".format(classes_names[0], probabilities[0]))
print("Our second guess is {} with the probability of {:.2f}%").format(classes_names[1], probabilities[1]))
print("And our third guess is {} with the probability of {:.2f}%").format(classes_names[2], probabilities[2]))
