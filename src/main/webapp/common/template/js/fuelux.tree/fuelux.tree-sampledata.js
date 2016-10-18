var DataSourceTree = function (options) {
    //console.log("----" + JSON.stringify(options));
    this._data = options.data;
};

DataSourceTree.prototype.data = function (options, callback) {
    var rootTree = null;

    if (!("name" in options) && !("type" in options)) {
        rootTree = this._data;
        callback({data: rootTree});
        return;
    } else if ("type" in options && options.type == "folder") {
        if ("additionalParameters" in options && "children" in options.additionalParameters)
            rootTree = options.additionalParameters.children;
        else rootTree = {};//no data
    }

    if (rootTree != null) {
        callback({data: rootTree});
    }

};

$.get("../../role/getMenuTree", function (response) {
    //alert(JSON.stringify(response));

    var treeDataSource = new DataSourceTree({data: response});

    $('#tree1').ace_tree({
        dataSource: treeDataSource,
        multiSelect: true,
        loadingHTML: '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
        'open-icon': 'icon-minus',
        'close-icon': 'icon-plus',
        'selectable': true,
        'selected-icon': 'icon-ok',
        'unselected-icon': 'icon-remove'
    });
});

