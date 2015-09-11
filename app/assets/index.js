jQuery(function ($) {
    var campaignId = $("#container").data("campaign-id");
    var ws = new WebSocket("ws://localhost:9000/api/ws");

    ws.onmessage = function (event) {
        var message = JSON.parse(event.data);
        if (message.id == campaignId) {
            if(message.incr == "clicks") {
                $("#clicks").text(parseInt($("#clicks").text()) + 1);
            }
            if (message.incr == "views") {
                $("#views").text(parseInt($("#views").text()) + 1);
            }
        }
    };

    function incr(selector) {
        var elem =  $(selector);
        elem.text(parseInt(elem.text()) + 1);
    }

});