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
