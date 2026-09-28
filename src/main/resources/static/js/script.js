async function send(url, method, data) {

    const options = {
        method: method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (data !== undefined) {
        options.body = JSON.stringify(data);
    }

    const response = await fetch(url, options);

    const text = await response.text();

    let result;

    try {
        result = JSON.parse(text);
    } catch {
        result = text;
    }

    if (!response.ok) {
        throw new Error(result.error || result);
    }

    return result;
}


/* ADD ROOM */

async function addRoom() {

    try {

        const room = {
            name: document.getElementById("roomName").value,
            capacity: Number(document.getElementById("roomCapacity").value),
            projector: document.getElementById("projector").checked,
            whiteboard: document.getElementById("whiteboard").checked,
            active: document.getElementById("active").checked
        };

        const result = await send("/rooms", "POST", room);

        document.getElementById("message").innerText =
            "Room created successfully. Room ID: " + result.id;

    } catch (error) {

        document.getElementById("message").innerText =
            error.message;
    }
}


/* LOAD ROOMS */

async function loadRooms() {

    try {

        const rooms = await send("/rooms", "GET");

        let html =
            "<table>" +
            "<tr>" +
            "<th>ID</th>" +
            "<th>Name</th>" +
            "<th>Capacity</th>" +
            "<th>Projector</th>" +
            "<th>Whiteboard</th>" +
            "<th>Active</th>" +
            "</tr>";

        rooms.forEach(room => {

            html +=
                "<tr>" +
                "<td>" + room.id + "</td>" +
                "<td>" + room.name + "</td>" +
                "<td>" + room.capacity + "</td>" +
                "<td>" + room.projector + "</td>" +
                "<td>" + room.whiteboard + "</td>" +
                "<td>" + room.active + "</td>" +
                "</tr>";

        });

        html += "</table>";

        document.getElementById("rooms").innerHTML = html;

    } catch (error) {

        alert(error.message);
    }
}