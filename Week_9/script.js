window.onload = function() {
    var t = document.getElementById("_body");
    var trs = t.getElementsByTagName("tr");
    var tds = null;

    elementList = Array();

    for (var i=0; i<trs.length; i++)
    {
        tds = trs[i].getElementsByTagName("td");
        title = tds[0].textContent;

        score = tds[2].textContent;

        console.log(title);
        console.log(score);

        e = new Element(title, "#417333", score);
        elementList.push(e);
    }
    createChart(40,40,elementList,80,68);
}

function createChart(marginBot, marginTop, listElements, rectWidth, spacing) {
    var canvas = document.getElementById("myCanvas");
    var can = canvas.getContext("2d");
    var width = canvas.clientWidth;
    var height = canvas.clientHeight;

    can.fillStyle = "#BBB891";
    can.fillRect(0, 0, canvas.width, canvas.height);

    var maxElement = getMaxElement(listElements);

    var rectHeight = height - marginTop;

    var x = 0 + spacing;
    var y = 0 + marginTop;


    var maxHeight = height - marginTop - marginBot;

    for(var i=0; i< listElements.length; i++) {
        var percentage = (listElements[i].value * 100) / maxElement.value;
        var currHeight = (percentage * maxHeight) / 100;

        can.fillStyle = "black";
        can.font = "bold 16px Arial";
        can.fillText(listElements[i].value, x, y + (maxHeight - currHeight) - 2);

        can.fillStyle = listElements[i].color;
        can.fillRect(x, y + (maxHeight - currHeight), rectWidth, maxHeight - ( y + (maxHeight - currHeight)) );
        can.fillStyle = "black";
        can.font = "bold 16px Arial";
        can.fillText(listElements[i].name, x, maxHeight + 16);
        x = x + spacing + rectWidth ;
    }
}

function getMaxElement(listElements) {
    var maxElement = listElements[0];
    for(var i=1; i<listElements.length; i++) {
        if(parseInt(listElements[i].value) > parseInt(maxElement.value)) {
            maxElement = listElements[i];
        }
    }
    return maxElement;
}



class Element {
    constructor(name, color, value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }
}