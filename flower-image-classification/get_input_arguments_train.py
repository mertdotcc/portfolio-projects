# AUTHOR: MERT ÖZTÜRK
# Contact: mert1ozturk@gmail.com

import argparse

def get_input_arguments_train():
    """
    Takes the argument from the user through the command line.
    Arguments:
    --dir: Type in the directory of your dataset.
    --model: Type in the NN model you want to train with. Available models are: vgg19, alexnet, densenet121.
    --checkpoint: Type in the directory of your pretrained model if you have any.
    --lr: Type in the learning rate you want.
    --hidden: Type in the number of hidden units you want.
    --epoch: Type in the number of epoch you want.
    --batch: Type in the value of the batch size you want.
    --step: Type in the number of train steps you want.
    --dropout: Type in the value of dropouts that will be used in the training set.
    --gpu: Choose between GPU and CPU. Note: GPU is highly advised.
    --categories: The file that contains name of the categories.
    --savedir: Type in the location you want the checkpoints to be saved at.
    """

    parser = argparse.ArgumentParser()

    parser.add_argument("--dir", type=str, default="flowers", help="Type in the directory of your dataset.")
    parser.add_argument("--model", type=str, default="vgg19", help="Type in the NN model you want to train with. Available models are: vgg19, alexnet, densenet121.")
    parser.add_argument("--checkpoint", type=str, default="checkpoint.pth", help="Type in the directory of your pretrained model if you have any.")
    parser.add_argument("--lr", type=float, default=0.001, help="Type in the learning rate you want.")
    parser.add_argument("--hidden", type=int, default=500, help="Type in the number of hidden units you want.")
    parser.add_argument("--epoch", type=int, default=5, help="Type in the number of epoch you want.")
    parser.add_argument("--batch", type=int, default=64, help="Type in the value of the batch size you want.")
    parser.add_argument("--step", type=int, default=3, help="Type in the number of train steps you want.")
    parser.add_argument("--dropout", type=float, default=0.5, help="Type in the value of dropouts that will be used in the training set.")
    parser.add_argument("--gpu", type=bool, default=True, help="Choose between GPU and CPU. Note: GPU is highly advised.")
    parser.add_argument("--categories", type=str, default="cat_to_name.json", help="The file that contains name of the categories.")
    parser.add_argument("--savedir", type=str, default="checkpoints", help="Type in the location you want the checkpoints to be saved at.")
