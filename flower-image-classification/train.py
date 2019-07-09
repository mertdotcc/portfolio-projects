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
