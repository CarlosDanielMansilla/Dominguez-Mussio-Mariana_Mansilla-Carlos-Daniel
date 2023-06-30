window.addEventListener('load', function(){

    const form = document.querySelector("#formularioOd")
    const matricula= this.document.querySelector("#matriculaOd")
    const nombre = document.querySelector("#nombreOd")
    const apellido = document.querySelector("#apellidoOd")
    

    form.addEventListener('submit', function (event) {
        event.preventDefault()
        const payload = {
            matricula: matricula.value,
            nombre: nombre.value,
            apellido: apellido.value
            
        }
        console.log(payload)

        const rawResponse = fetch('odontologos/registrar', {
            method: 'POST',
            headers:{
                        'Content-Type': 'application/json'
                    },
            body: JSON.stringify(payload)
        });
        console.log(rawResponse)

        alert("El odontologo fue registrado con exito!");
        window.location.href = "index.html";

        

    });


    const formEdit = document.querySelector("#formularioEdit")
    formEdit.addEventListener('submit', function (event) {

        const id= document.querySelector("#id")
        const matricula = document.querySelector("#matriculaEdit")
        const nombre = document.querySelector("#nombreEdit")
        const apellido = document.querySelector("#apellidoEdit")

        event.preventDefault()
        const payload2 = {
            id: id.value,
            matricula: matricula.value,
            nombre: nombre.value,
            apellido: apellido.value
            
        }
        console.log(payload2)

        const rawResponse = fetch('odontologos/actualizar', {
            method: 'PUT',
            headers:{
                        'Content-Type': 'application/json'
                    },
            body: JSON.stringify(payload2)
        });
        console.log(rawResponse)

        alert("El odontologo fue actualizado con exito!");
        window.location.href = "index.html";

        

    });


    const formDelete = document.querySelector("#formularioDelete")
    formDelete.addEventListener('submit', function (event) {

        const idDelete= document.querySelector("#idDelete").value;

        event.preventDefault()

        const rawResponse = fetch('odontologos/eliminar/' + idDelete, {
            method: 'DELETE',
            headers:{
                        'Content-Type': 'application/json'
                    },
        });
        console.log(rawResponse)

        alert("El odontologo fue eliminado con exito!");
        window.location.href = "index.html";

        

    });


})