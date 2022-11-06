RTE_DefaultConfig.url_base='richtexteditor'
let editor1 = new RichTextEditor("#div_editor1");

const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/annualreviewnotes"

const formatAnnualReview = (notes) => {
    let noteCard = ""

    notes.forEach(obj => {
    let htmlNoteCard = `
        <p><b>Jira Ticket:</b> ${obj.jiraTicket}</p>
        <p><a href=${obj.githubLink} target=_blank> <b>GitHub Link</b></a></p>
        <p><b>Date:</b> ${(obj.dateNote).split("T")[0]}</p>
        <p><b>Note:</b><br> ${(obj.annualNote)}</p>
        <p></p>
        `

        noteCard+=htmlNoteCard;
    })
    editor1.setHTMLCode(noteCard);
}

async function getAnnualNoteByUser(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
          method: "GET",
          headers: headers
      })
          .then(response => response.json())
          .then(data => formatAnnualReview(data))
}

getAnnualNoteByUser(userId)




