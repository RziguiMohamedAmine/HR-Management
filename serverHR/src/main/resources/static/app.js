const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8008/gs-guide-websocket', // WebSocket URL
    onConnect: (frame) => {
        console.log('Connected: ' + frame);

        // Subscribe to the admin-specific topic
        const adminId = 1; // Hardcoded admin ID

        // Automatically subscribe to the /topic/greetings when connected
        stompClient.subscribe(`/topic/admin-${adminId}`, (notification) => {
            showNotification(JSON.parse(notification.body).message); // Display the notification message
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
