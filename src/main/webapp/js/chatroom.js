// Scroll function
function scrollToBottom() {
	$("#chatBox").scrollTop($("#chatBox")[0].scrollHeight);
}
// Async post function
function postMessage() {
	var author = $("#author").val();
	var text = $("#text").val();
	var params = {author: author, text: text};
	
	$.post(
		"/", 
		params,
		function() {
			$("#text").val("");
		}
	);
	return false;
}
// Polling function
function poll() {
	window.setTimeout(updateMessages, 1000);
}
// Refresh function
function updateMessages() {
	var params = {update: getLastUpdated()};
	$.get(
		"/", 
		params,
		function(data) {
			$("#chatBox").append(data);
			scrollToBottom();
		},
		"html"
	);
	poll();
}
// Get last updated time
function getLastUpdated() {
	var message = $(".message:last");
	return message.find("#lastUpdate").val();	
}