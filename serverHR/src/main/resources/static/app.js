const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8008/gs-guide-websocket', // WebSocket URL
    onConnect: (frame) => {
        console.log('Connected: ' + frame);

        // Automatically subscribe to the /topic/greetings when connected
        stompClient.subscribe('/topic/greetings', (greeting) => {
            showNotification(JSON.parse(greeting.body).message);  // Show the greeting message
        });
    },
    onWebSocketError: (error) => {
        console.error('Error with websocket', error);
    },
    onStompError: (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    }
});

// Automatically connect when the page is loaded
$(function () {
    stompClient.activate(); // Activate the WebSocket connection automatically
});

// Function to display the greeting message
function showNotification(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
