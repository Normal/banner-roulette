jQuery(function ($) {
    var campaignId = $("#container").data("campaign-id");
    var ws = new WebSocket("ws://localhost:9000/api/ws");

    var clicks = $("#clicks");
    var views = $("#views");

    ws.onmessage = function (event) {
        var message = JSON.parse(event.data);
        if (message.id == campaignId) {
            if (message.incr == "clicks") {
                incr(clicks);
            }
            if (message.incr == "views") {
                incr(views);
            }
            $("#conversions").text(calculate_conversions(clicks, views))
        }
    };

    function incr(selector) {
        var elem = $(selector);
        elem.text(parseInt(elem.text()) + 1);
    }

    function calculate_conversions(clicks, views) {
        if (views.text() == 0) return 0;
        return parseInt(clicks.text() / views.text() * 100);
    }

});