var stompClient = null;

jQuery("[id^=tab-]").on("click", switchTab);
jQuery("[id^=tab-]").on("click", getMessages);
jQuery("[id^=tab-]").on("click", connect);
// jQuery("[id=add-group-icon]").on("click", showGroupPanel);

function switchTab(){
    var messageBoardId = $(this).data("messageBoardId");
    $("[id^=content-]").css("display", "none");
    $("#content-" + messageBoardId).css("display", "block");
}

function getMessages() {
    var groupPanel = $("#group-panel");
    if(groupPanel != null){
        groupPanel.remove();
    }
    var messageBoardId = $(this).data("messageBoardId");
    var userId = $(this).data("userId");
    $("#content-" + $(this).data("messageBoardId")).empty();
    $.get("/chat/messages?messageBoardId=" + $(this).data("messageBoardId"), function (data) {
        $.each(data.messageResources, function (i, val) {
            var userId = $("#tab-" + val.messageBoardId).data("userId");
            var classType;
            if(userId == val.userResource.userId){
                classType = "message self";
            }else{
                classType = "message";
            }
            $("#content-" + val.messageBoardId).append(
                $('<p class="'+ classType + '"><span class="icon"><img src="'
                    + val.userResource.imageFilePath
                    + '"><span class="name">'
                    + val.userResource.displayName
                    + '</span></span><span class="body">'
                    + val.comment
                    + '</span></p>')
            )
        });
        $("#content-" + messageBoardId)
            .append($('<div id="form-'
                + messageBoardId
                + '" class="form"><label for="comment">Type message.</label><input type="text" id="input-'
                + messageBoardId
                + '" class="comment" name="comment" data-message-board-id="'
                + messageBoardId
                + '"placeholder="message here..." onkeypress="keyTypeHandler(event.keyCode, this);"/><button id="send-'
                + messageBoardId
                + '" data-message-board-id="'
                + messageBoardId
                + '" data-user-id="'
                + userId
                + '" data-request-context-path="/chat/messge/send" type="button">Send</button></div>'));
        $("#send-" + messageBoardId).on("click", sendMessage);
    });
}

function connect() {
    var messageBoardId = $(this).data("messageBoardId");
    var userId = $(this).data("userId");
    var socket = null;
    var endpoint = "/messages/" + messageBoardId;
    var subscription = "/topic/user-" + userId;
    if(stompClient !== null){
        stompClient.disconnect();
    }
    socket = new SockJS(endpoint);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(subscription, function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function keyTypeHandler(code, form){
    if(code == 13){
        var messageBoardId = $(form).data("messageBoardId");
        var userId = $("#send-" + messageBoardId).data("userId");
        var endpoint = "/chat/messages/" + messageBoardId;
        stompClient.send(endpoint, {}, JSON.stringify(
            {'comment': $("#input-" + messageBoardId).val(),
                'userResource': { 'userId' : userId } ,
                'messageBoardId':  messageBoardId }));
    }
}

function sendMessage() {
    var messageBoardId = $(this).data("messageBoardId");
    var endpoint = "/chat/messages/" + messageBoardId;
    var userId = $(this).data("userId");
    stompClient.send(endpoint, {}, JSON.stringify(
        {'comment': $("#input-" + messageBoardId).val(),
            'userResource': { 'userId' : userId } ,
            'messageBoardId': messageBoardId }));
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function showMessage(message){
    var userId = $("#tab-" + message.messageBoardId).data("userId");
    if(userId == message.userResource.userId){
        classType = "message self";
    }else{
        classType = "message";
    }
    $("#form-" + message.messageBoardId)
        .before($('<p class="'+ classType + '"><span class="icon"><img src="'
            + message.userResource.imageFilePath
            + '"><span class="name">'
            + message.userResource.displayName
            + '</span></span><span class="body">'
            + message.comment
            + '</span></p>'));
    $("#input-" + message.messageBoardId).val("");
}

// function showGroupPanel(event){}