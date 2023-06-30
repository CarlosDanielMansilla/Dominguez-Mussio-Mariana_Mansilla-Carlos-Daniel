window.addEventListener('load', function(){


    const form = document.querySelector("#formulario")
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const dni = document.querySelector("#dni")
    const fecha = document.querySelector("#fecha")
    const calle = document.querySelector("#calle")
    const numero = document.querySelector("#numero")
    const localidad = document.querySelector("#localidad")
    const provincia = document.querySelector("#provincia")

    form.addEventListener('submit', function (event) {
        event.preventDefault()
        const payload = {
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            fechaIngreso: fecha.value,
            domicilio:{
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
            
        }
        console.log(payload)

        const rawResponse = fetch('pacientes/registrar', {
            method: 'POST',
            headers:{
                        'Content-Type': 'application/json'
                    },
            body: JSON.stringify(payload)
        });
        console.log(rawResponse)

        alert("El paciente fue registrado con exito!");
        window.location.href = "index.html";

        

    });


    const formEdit = document.querySelector("#formularioEdit")
    formEdit.addEventListener('submit', function (event) {

        const id= document.querySelector("#id")
        const nombre = document.querySelector("#nombreEdit")
        const apellido = document.querySelector("#apellidoEdit")
        const dni = document.querySelector("#dniEdit")
        const fecha = document.querySelector("#fechaEdit")
        const idDoc= document.querySelector("#idDoc")
        const calle = document.querySelector("#calleEdit")
        const numero = document.querySelector("#numeroEdit")
        const localidad = document.querySelector("#localidadEdit")
        const provincia = document.querySelector("#provinciaEdit")

        event.preventDefault()
        const payload2 = {
            id: id.value,
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            fechaIngreso: fecha.value,
            domicilio:{
                id: idDoc.value,
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
            
        }
        console.log(payload2)

        const rawResponse = fetch('pacientes/actualizar', {
            method: 'PUT',
            headers:{
                        'Content-Type': 'application/json'
                    },
            body: JSON.stringify(payload2)
        });
        console.log(rawResponse)

        alert("El paciente fue actualizado con exito!");
        window.location.href = "index.html";

        

    });


    const formDelete = document.querySelector("#formularioDelete")
    formDelete.addEventListener('submit', function (event) {

        const idDelete= document.querySelector("#idDelete").value;

        event.preventDefault()

        const rawResponse = fetch('pacientes/eliminar/' + idDelete, {
            method: 'DELETE',
            headers:{
                        'Content-Type': 'application/json'
                    },
        });
        console.log(rawResponse)

        alert("El paciente fue eliminado con exito!");
        window.location.href = "index.html";

        

    });


})