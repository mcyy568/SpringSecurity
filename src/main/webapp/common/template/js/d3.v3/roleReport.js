function viewRoleTree(id) {
    var width = 500, height = 400;

    var tree = d3.layout.tree()
        .size([width - 250, height - 50])
        .separation(function (a, b) {
            return (a.parent == b.parent ? 1 : 2);
        });

    var diagonal = d3.svg.diagonal()
        .projection(function (d) {
            return [d.y, d.x];
        });

    $("#viewRoleTreeDiv").html("");

    var svg = d3.select("#viewRoleTreeDiv").append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(40,0)");

    $.get("../../role/getRoleMenu/" + id, function (response){
        var nodes = tree.nodes(response);

        var links = tree.links(nodes);

        var link = svg.selectAll(".link")
            .data(links)
            .enter()
            .append("path")
            .attr("class", "link")
            .attr("d", diagonal);

        var node = svg.selectAll(".node")
            .data(nodes)
            .enter()
            .append("g")
            .attr("class", "node")
            .attr("transform", function (d) {
                return "translate(" + d.y + "," + d.x + ")";
            });

        node.append("circle")
            .attr("r", 4.5);

        node.append("text")
            .attr("dx", function (d) {
                return d.children ? - 8 : 8;
            })
            .attr("dy", 3)
            .style("text-anchor", function (d) {
                return d.children ? "end" : "start";
            })
            .text(function (d) {
                return d.name;
            });
    });
}

