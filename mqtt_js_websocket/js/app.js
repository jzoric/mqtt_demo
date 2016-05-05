$(document).ready(function() {
    var client;
    var messages = $("#messages");

    $("#connect").click(function() {
        var url = $("#url").val();
        var port = $("#port").val();

        client = new Messaging.Client(url, parseInt(port), "myclientid_1");

        var options = {
            timeout: 3,
            cleanSession: true,
            onSuccess: function() {
                $("#connected_status").text("YES");
                $("#connected_status").removeClass("alert").addClass("success");
                messageLog("Connected");
            },
            onFailure: function(message) {
                messageLog("Connection failed: " + message);
            }
        };
        client.connect(options);


        client.onConnectionLost = function(responseObject) {
            $("#connected_status").text("NO");
            $("#connected_status").removeClass("success").addClass("alert");
            messageLog("Connection lost: " + responseObject.errorMessage);
        };

        client.onMessageArrived = function(message) {
            var data = message.payloadString.split(";");
            if(data[1]===undefined){
              messageLog(message.destinationName + ' | ' + data);
              $("#temp_value").text("-");
              $("#hum_value").text("-");
              return;
            }
            var temp = data[0] + "°";
            var hum = data[1] + "%";

            $("#temp_value").text(temp);
            $("#hum_value").text(hum);
            var msg = "Temp: " + temp + " Hum: " + hum;
            messageLog(message.destinationName + ' | ' + msg);
        };

        toogleButtonVisibility("#disconnect", this);
    });



    $("#subscribe").click(function() {
        var topic = $("#topic").val();
        client.subscribe(topic, {
            qos: 0
        });
        messageLog("Subscribed to: " + topic);
        setSubscribeStatus(topic);
        toogleButtonVisibility("#unsubscribe", this);
    });

    $("#unsubscribe").click(function() {
        var topic = $("#topic").val();
        client.unsubscribe(topic, {});
        messageLog("Unsubscribed: " + topic);
        setSubscribeStatus("");
        toogleButtonVisibility("#subscribe", this);
    });

    $("#clearLog").click(function() {
        $("#messages").children().remove();
    });

    $("#disconnect").click(function() {
        client.disconnect();
        setSubscribeStatus("");
        toogleButtonVisibility("#connect", this);
    });


    // Helper functions
    var toogleButtonVisibility = function(showButton, hideButton) {
        $(hideButton).addClass('invisible');
        $(showButton).removeClass('invisible');
    };

    var setSubscribeStatus = function(topic) {
        $("#subscribed_topic").text(topic);
    };

    var messageLog = function(message) {
        message = message + " (" + moment().format('D MMMM YYYY, h:mm:ss A') + ")";
        messages.append("<div class='border-box'><span>" + message + "</span></div><hr>");
    };

});
