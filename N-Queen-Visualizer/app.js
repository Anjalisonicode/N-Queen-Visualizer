

const playButton = document.getElementById("play-button");
const queenBoard = document.getElementById("queen-board");
const numberbox = document.getElementById("numberbox");

// Event handler for the play button
playButton.onclick = async function () {
    const n = numberbox.value;

    // Validate input
    if (n < 1 || n > 8) {
        alert("Number of queens must be between 1 and 8.");
        return;
    }

    try {
        // Make the fetch request to the backend
        const response = await fetch("http://localhost:8080/api/nqueen/solve", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ n: parseInt(n) }), // Send N as JSON payload
        });

        // Check if the response is OK
        if (!response.ok) {
            throw new Error(`Error: ${response.status} ${response.statusText}`);
        }

        // Parse the response JSON
        const solutions = await response.json();

        // Render the solutions on the board
        renderBoards(solutions);
    } catch (error) {
        // Log and alert the error
        console.error("Fetch error:", error);
        alert("An error occurred while fetching solutions. Please try again.");
    }
};

// Function to render the N-Queen solutions on the board
function renderBoards(solutions) {
    queenBoard.innerHTML = ""; // Clear previous boards

    // Iterate over each solution
    solutions.forEach((solution, index) => {
        const boardDiv = document.createElement("div");
        boardDiv.classList.add("solution-container");

        // Add a header for each solution
        const header = document.createElement("h4");
        header.textContent = `Solution ${index + 1}`;
        boardDiv.appendChild(header);

        // Create the chessboard
        const table = document.createElement("table");
        table.classList.add("chessboard");

        solution.forEach(row => {
            const tr = document.createElement("tr");
            row.split("").forEach(cell => {
                const td = document.createElement("td");
                td.textContent = cell === "Q" ? "♛" : "";
                td.style.backgroundColor = cell === "Q" ? "#ffca3a" : "#ffffff";
                tr.appendChild(td);
            });
            table.appendChild(tr);
        });

        boardDiv.appendChild(table);
        queenBoard.appendChild(boardDiv);
    });
}
