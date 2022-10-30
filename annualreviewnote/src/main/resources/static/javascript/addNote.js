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

let annualNoteBody = document.getElementById(`note-body`)
let updateNoteBtn = document.getElementById("update-note-button")

const btnCompanyNameSubmit = document.querySelector("#companyName")
const btnPositionTitleSubmit = document.querySelector("#PositionTitle")
const btnGithubLinkSubmit = document.querySelector("#register-github")
const btnJiraTicketSubmit = document.querySelector("#register-jiraTicket")
const btnDateSubmit = document.querySelector("#register-dateNote")
const btnNoteSubmit = document.querySelector("#register-note")

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

        await addNote(bodyObj, professionalInfoId);
//        document.getElementById("register-note").value = '',
//        companyNameList.innerHTML = ''
//        companyTitleList.innerHTML = ''
//                        .then (res => {
//                            res.data.forEach(elem => {
//                                let companyCard = document.createElement("div")
//                                 companyCard.classList.add("class-m")
//                                 companyCard.innerHTML = `
//                                <option value=${elem.register-companyName}</option>
//                                <option value=${elem.register-companyTitle}</option>
//                                `
//                            })
//                        }).catch(err => console.log(err))
//
//                        companyNameList.append (companyCard);
//                        companyTitleList.append (companyCard);
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
    }
}

async function getAnnualNoteByUser(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
          method: "GET",
          headers: headers
      })
          .then(response => response.json())
          .then(data => createNoteCards(data))
          .catch(err => console.error(err))
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
        body: annualNoteBody.value
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
     annualNoteBody.innerText = ''
     annualNoteBody.innerText = obj.annualNote
     updateNoteBtn.setAttribute('data-note-id', obj.id)
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
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">jiraTicket ${obj.jiraTicket}</p>
                    <p class="card-text">githubLink ${obj.githubLink}</p>
                    <p class="card-text">dateNote ${obj.dateNote}</p>
                    <p class="card-text">annualNote ${obj.annualNote}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
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

function handleLogout(){
     let c = document.cookie.split(";");
     for(let i in c){
         document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
     }
 }

updateNoteBtn.addEventListener("click", (e) => {
    let noteId = e.target.getAttribute('data-note-id')
    handleNoteEdit(annualreviewnotesId);
})

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
          <option value=${elem.id}>pos</option>
         `
       PositionTitle.innerHTML += Position
       companyName.innerHTML += CompanyName
       })
    })
}

submitForm.addEventListener("submit", handleSubmitCompany)
submitFormAddNote.addEventListener("submit", handleSubmit)
getAnnualNoteByUser(userId);
getCompanyInfo()

