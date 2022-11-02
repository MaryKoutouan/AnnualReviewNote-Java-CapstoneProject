function showAddNoteForm() {
   document.getElementById('form-createnote').style.display = "block";
   document.getElementById('form-createCompany').style.display = "none";
}
function showAddCompanyForm() {
   document.getElementById('form-createCompany').style.display = "block";
   document.getElementById('form-createnote').style.display = "none";
}
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const companyNameList = document.querySelector("#register-companyName")
const companyTitleList = document.querySelector("#register-PositionTitle")

const submitForm = document.getElementById("form-addCompany")
const submitFormAddNote = document.getElementById("form-addNote")
const annualNoteContainer = document.querySelector("#annualNoteContainer")

const btnCompanyNameSubmit = document.querySelector("#companyName")
const btnPositionTitleSubmit = document.querySelector("#PositionTitle")
const btnGithubLinkSubmit = document.querySelector("#register-github")
const btnJiraTicketSubmit = document.querySelector("#register-jiraTicket")
const btnDateSubmit = document.querySelector("#register-dateNote")
const btnNoteSubmit = document.querySelector("#register-note")
//const btnTitleNoteSubmit = document.querySelector("#notes__title")

let updateBtnGithubLinkSubmit = document.getElementById("update-register-github")
let updateBtnJiraTicketSubmit = document.getElementById("update-register-jiraTicket")
let updateBtnDateSubmit = document.getElementById("update-register-dateNote")
let updateAnnualNoteBody = document.getElementById("update-note-body")
let updateNoteBtn = document.getElementById("update-submit-button")

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = "http://localhost:8080/api/annualreviewnotes"
const baseUrlCompany = "http://localhost:8080/api/professionalinformation/users"

const handleSubmitCompany = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        companyInfo: companyNameList.value,
        companyTitle: companyTitleList.value
    }
    const response = await fetch(`${baseUrlCompany}/${userId}`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        }).then(response => alert("Successfully added"))
}

const handleSubmit = async (e) =>{
        e.preventDefault()

        let bodyObj = {
            githubLink: btnGithubLinkSubmit.value,
            jiraTicket: btnJiraTicketSubmit.value,
            annualNote: btnNoteSubmit.value,
            dateNote: btnDateSubmit.value
        }

        let professionalInfoId = btnCompanyNameSubmit.value
//        let noteTitle = btnTitleNoteSubmit.value

        await addNote(bodyObj, professionalInfoId);

}

async function addNote(obj, professionalInfoId) {
    const response = await fetch(`${baseUrl}/users/${userId}/${professionalInfoId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getAnnualNoteByUser(userId);
//            sort((a,b) => {
////            return new Date(a.updated) > new Date(b.updated) ? -1 : 1;});
//    }
}
}

async function getAnnualNoteByUser(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
          method: "GET",
          headers: headers
      })
          .then(response => response.json())
          .then(data => createNoteCards(data))
//          .catch(err => console.error(err))
}

async function handleDelete(annualreviewnotesId) {
    await fetch(`${baseUrl}/${annualreviewnotesId}`, {
          method: "DELETE",
          headers: headers
      })
          .catch(err => console.error(err))

      return getAnnualNoteByUser(userId);
 }

 async function handleNoteEdit(annualreviewnotesId) {
    let bodyObj = {
        id: annualreviewnotesId,
        githubLink: updateBtnGithubLinkSubmit.value,
        jiraTicket: updateBtnJiraTicketSubmit.value,
        annualNote: updateAnnualNoteBody.value
        }
        await fetch(baseUrl, {
              method: "PUT",
              body: JSON.stringify(bodyObj),
              headers: headers
          })
              .catch(err => console.error(err))

          return getAnnualNoteByUser(userId);
 }

 const populateModal = (obj) => {
    updateBtnGithubLinkSubmit.innerText = ''
    updateBtnGithubLinkSubmit.innerText = obj.githubLink
    updateBtnJiraTicketSubmit.innerText = ''
    updateBtnJiraTicketSubmit.innerText = obj.jiraTicket
    updateAnnualNoteBody.innerText = ''
    updateAnnualNoteBody.innerText = obj.annualNote
    document.getElementById('myModal').style.display = "block";
    updateNoteBtn.setAttribute('annual-note-id', obj.id)
 }

 async function getAnnualNoteById(annualNoteId){
     await fetch(`${baseUrl}/${annualNoteId}`, {
         method: "GET",
         headers: headers
     })
         .then(res => res.json())
         .then(data => populateModal(data))
         .catch(err => console.error(err.message))
 }

 const createNoteCards = (annualNotesList) => {
    annualNoteContainer.innerHTML = ''
    annualNotesList.forEach(obj => {
        let noteCard = document.createElement("div")

        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
            <div class="card d-flex" style="float:left; width:300px; height:300px;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text"><b>Jira Ticket:</b> ${obj.jiraTicket}</p>
                    <p class="card-text"><a href=${obj.githubLink} target=_blank> <b>GitHub Link</b></a></p>
                    <p class="card-text"><b>Date:</b> ${(obj.dateNote).split("T")[0]}</p>
                    <p class="card-text"><b>Note:</b><br> ${(obj.annualNote).split(' ').slice(0, 45).join(' ')} <span id="dots">...</span><span id="more">${(obj.annualNote).split(' ').slice(46, -1).join(' ')}</span></p>
                    <button onclick="ReadMoreFn()" id="BtnReadMore">Read more</button>
                    <div class="d-flex justify-content-between">

                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">
                        Delete
                        </button>


                        <button onclick="getAnnualNoteById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#note-edit-modal">
                        Edit
                        </button>
                        </div>
                    </div>
                </div>
            `
        annualNoteContainer.append(noteCard);
    })
 }

 function ReadMoreFn() {
   let dots = document.getElementById("dots");
   let moreText = document.getElementById("more");
   let btnText = document.getElementById("BtnReadMore");

   if (dots.style.display === "none") {
     dots.style.display = "inline";
     btnText.innerHTML = "Read more";
     moreText.style.display = "none";
   } else {
     dots.style.display = "none";
     btnText.innerHTML = "Read less";
     moreText.style.display = "inline";
   }
 }

function handleLogout(){
     let c = document.cookie.split(";");
     for(let i in c){
         document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
     }
 }

updateNoteBtn.addEventListener("click", (e) => {
    let annualNoteId = e.target.getAttribute('annual-note-id')
     document.getElementById('myModal').style.display = "none";
    handleNoteEdit(annualNoteId);
})

// When the user clicks on <span> (x), close the modal
document.getElementsByClassName("close")[0].onclick = function() {
  document.getElementById('myModal').style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == document.getElementById("myModal")) {
    document.getElementById('myModal').style.display = "none";
  }
}

const getCompanyInfo = async () => {
    companyName.innerHTML = ''
    PositionTitle.innerHTML = ''
    await fetch(`${baseUrlCompany}/${userId}`, {
      method: "GET",
      headers: headers
  })
   .then(response => response.json())
   .then(data => {
       data.forEach(elem => {
       let CompanyName = `
          <option value=${elem.id}>${elem.companyInfo}</option>
          `
       let Position = `
          <option value=${elem.id}>${elem.companyTitle}</option>
         `
       PositionTitle.innerHTML += Position
       companyName.innerHTML += CompanyName
       })
    })
}

if (userId) {
    if (document.getElementById('LoginFirstMessage').style.display === "block") {
            document.getElementById('LoginFirstMessage').style.display = "none";
            document.getElementById('MyNoteComponent').style.display = "block";
    } else {
        document.getElementById('LoginFirstMessage').style.display = "block"
        document.getElementById('MyNoteComponent').style.display = "none";
    }
}

//Sort notes
//const filterNotes = ({
//    setSelectedVal;
//    dateNote;
//})
//
//  const handleSelectSort = (e) => {
//    const target = e.target.value;
//    setSelectedVal(target);
//
//if (target === 'desc') {
//      const SortedNotes = dateNote.sort((a, b) =>
//        a.LastEdit < b.LastEdit ? 1 : -1
//      );
//      setNotes(SortedNotes);
//
//      // Sort by oldest notes
//    } else if (target === 'asc') {
//      const SortedNotes = dateNote.sort((a, b) =>
//        b.LastEdit < a.LastEdit ? 1 : -1
//      );
//      setNotes(SortedNotes);




submitForm.addEventListener("submit", handleSubmitCompany)
submitFormAddNote.addEventListener("submit", handleSubmit)
getAnnualNoteByUser(userId);
getCompanyInfo()