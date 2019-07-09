def classifier_create(model, n_input_units, n_hidden_units1, n_hidden_units2, dropout_rate):

    # No need
    for p in model.parameters():
        p.requires_grad = False

    from collections import OrderedDict
    classifier = nn.Sequential(OrderedDict([
                              ('fc1', nn.Linear(n_input_units, n_hidden_units1)),
                              ('relu', nn.ReLU()),
                              ('dropout1', nn.Dropout(dropout_rate)),
                              ('fc2', nn.Linear(n_hidden_units1, n_hidden_units2)),
                              ('relu', nn.ReLU()),
                              ('dropout2', nn.Dropout(dropout_rate)),
                              ('fc3', nn.Linear(n_hidden_units2, 102)),
                              ('output', nn.LogSoftmax(dim=1))
                              ]))

    model.classifier = classifier

    return model
