const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const companyNameList = document.querySelector("#register-companyName")
const companyTitleList = document.querySelector("#register-PositionTitle")

const submitForm = document.getElementById("form-addCompany")
const submitFormAddNote = document.getElementById("form-addNote")
const annualNoteContainer = document.querySelector("#annualNoteContainer")


const SelectCompanyNameSubmit = document.querySelector("#companyName")
const PositionTitle = document.querySelector("#PositionTitle")
const btnGithubLinkSubmit = document.querySelector("#register-github")
const btnJiraTicketSubmit = document.querySelector("#register-jiraTicket")
const btnDateSubmit = document.querySelector("#register-dateNote")
const btnNoteSubmit = document.querySelector("#register-note")


const btnCompanyNameGenerate = document.querySelector('#generate-reviewCompanyName')
const btnPositionTitleGenerate = document.querySelector('#generate-reviewPositionTitle')
const generateForm = document.querySelector("#form-generateCompany")


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

function showAddNoteForm() {
    if (document.getElementById('form-createnote').style.display === "block") {
       document.getElementById('form-createnote').style.display = "none";
       document.getElementById('form-createCompany').style.display = "none";
       document.getElementById('form-generateReview').style.display = "none";
       document.getElementById('myNoteDiv').style.display = "block";
    } else {
       document.getElementById('form-createnote').style.display = "block";
       document.getElementById('form-createCompany').style.display = "none";
       document.getElementById('form-generateReview').style.display = "none";
       document.getElementById('myNoteDiv').style.display = "block";
       }
}
function showAddCompanyForm() {
    if (document.getElementById('form-createCompany').style.display === "block") {
       document.getElementById('form-createCompany').style.display = "none";
       document.getElementById('form-createnote').style.display = "none";
       document.getElementById('form-generateReview').style.display = "none";
       document.getElementById('myNoteDiv').style.display = "block";
    } else {
       document.getElementById('form-createCompany').style.display = "block";
       document.getElementById('form-createnote').style.display = "none";
       document.getElementById('form-generateReview').style.display = "none";
       document.getElementById('myNoteDiv').style.display = "block";
       }
}

function showGenerateReviewForm() {
       document.getElementById('form-generateReview').style.display = "block";
       document.getElementById('form-createnote').style.display = "none";
       document.getElementById('form-createCompany').style.display = "none";
       document.getElementById('myNoteDiv').style.display = "none";
}

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
        let professionalInfoId = SelectCompanyNameSubmit.value
        await addNote(bodyObj, professionalInfoId);

}


const handleSubmitGenerateNote = async (e) => {
    e.preventDefault()
     window.location.replace('http://localhost:8080/generatereview.html')
 }

async function addNote(obj, professionalInfoId) {
    const response = await fetch(`${baseUrl}/users/${userId}/${professionalInfoId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        window.location.replace('http://localhost:8080/addNote.html');
        return getAnnualNoteByUser(userId);
        }
}

async function getAnnualNoteByUser(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
          method: "GET",
          headers: headers
      })
          .then(response => response.json())
          .then(data => {
                createNoteCards(data)
          })
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
    let i = 0
    let j = 0
    let listOfColor = ['blue', 'green', 'yellow', 'brown', 'purple', 'orange']
    annualNotesList.forEach(obj => {
        let noteCard = document.createElement("div")
        if (j === 6) {j = 0}
        noteCard.classList.add("m-2")
        let htmlNoteCard = `<div class="col-md-4 col-sm-6 content-card" >
            <div class="card-big-shadow">
               <div class="card card-just-text" data-background="color" data-color="" data-radius="none">
                   <p class="card-text"><b>Jira Ticket:</b> ${obj.jiraTicket}</p>
                   <p class="card-text"><a href=${obj.githubLink} target=_blank> <b>GitHub Link</b></a></p>
                   <p class="card-text"><b>Date:</b> ${(obj.dateNote).split("T")[0]}</p>
                   <p class="card-text"><b>Note:</b><br> ${(obj.annualNote).split(' ').slice(0, 40).join(' ')} <span id="dots">...</span><span id="more" style="display: none;">${(obj.annualNote).split(' ').slice(41, -1).join(' ')}</span></p>

                   <div class="d-flex justify-content-between">
                       <button class="btn" onclick="ReadMoreFn()" id="BtnReadMore">Read more</button>
                       <button onclick="getAnnualNoteById(${obj.id})" type="button" class="btn btn-primary"
                         data-bs-toggle="modal" data-bs-target="#note-edit-modal">Edit </button>
                       <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                   </div>
               </div>
            </div>
            </div>
               `
        htmlNoteCard = htmlNoteCard.replace('onclick="ReadMoreFn()"', 'onclick="ReadMoreFn('+i+')"')
        htmlNoteCard = htmlNoteCard.replace('data-color=""', 'data-color='+listOfColor[j])
        noteCard.innerHTML = htmlNoteCard;
        annualNoteContainer.append(noteCard);
        i += 1
        j += 1
    })
 }

 function ReadMoreFn(num) {
   let dots = document.querySelectorAll("#dots")[num]
   let moreText = document.querySelectorAll("#more")[num]
   let btnText = document.querySelectorAll("#BtnReadMore")[num]

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
    document.getElementById('generate-reviewCompanyName').innerHTML = ''
    document.getElementById('generate-reviewPositionTitle').innerHTML = ''

    await fetch(`${baseUrlCompany}/${userId}`, {
      method: "GET",
      headers: headers
  })
   .then(response => response.json())
   .then(data => {
       data.forEach(elem => {
       let CompanyNameValue = `
          <option value=${elem.id}>${elem.companyInfo}</option>
          `
       let Position = `
          <option value=${elem.id}>${elem.companyTitle}</option>
         `
       PositionTitle.innerHTML += Position
       companyName.innerHTML += CompanyNameValue
       document.getElementById('generate-reviewCompanyName').innerHTML += CompanyNameValue
       document.getElementById('generate-reviewPositionTitle').innerHTML += Position
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

async function checkUserCompany(userId) {
 await fetch('http://localhost:8080/api/professionalinformation/users/' + userId,{
                  method: "GET",
                  headers: {'Content-Type':'application/json'}
              }).then(response => response.json()).then(data => {
              if (data.status === 400 || data.length === 0) {
                document.getElementById('form-createCompany').style.display = "block";
                document.getElementById('myNotesH1').style.display = "none";
              }
              }).catch(err => console.error(err))
}



submitForm.addEventListener("submit", handleSubmitCompany)
submitFormAddNote.addEventListener("submit", handleSubmit)

generateForm.addEventListener("submit", handleSubmitGenerateNote)

getAnnualNoteByUser(userId);
getCompanyInfo()