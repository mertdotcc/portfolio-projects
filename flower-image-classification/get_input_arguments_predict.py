# AUTHOR: MERT ÖZTÜRK
# Contact: mert1ozturk@gmail.com

import argparse

def get_input_arguments_predict():
    parser.add_argument("--checkpoint", type=str, default="checkpoint.pth", help="Type in the directory where you want to archive the train data.")
    parser.add_argument("--savedir", type=str, default="checkpoints", help="Type in the location you want the checkpoints to be saved at.")
    parser.add_argument("--categories", type=str, default="cat_to_name.json", help="The file that contains name of the categories.")
    parser.add_argument("--gpu", type=bool, default=True, help="Choose between GPU and CPU. Note: GPU is highly advised.")
    parser.add_argument('--image', type=str, default='test_image.jpg', help="Type in the folder that contains the images that you would like to make predictions on.")
    parser.add_argument('--topk', type=int, default=5, help="Type in the number of top K classes to be returned.") 
