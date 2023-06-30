window.addEventListener('load', function(){

    const form = document.querySelector("#formularioT")
    const selectPaciente= document.querySelector("#pacienteT")
    const selectOdontologo = document.querySelector("#odontologoT")
    const fechaHora = document.querySelector("#fechaHora")


    selectPaciente.addEventListener('click',function(event){
        const rawResponse = fetch('pacientes', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });
    
            rawResponse.then(function(response) {
                return response.json();
            }).then(function(data) {
                // Aquí puedes trabajar con los datos en formato JSON
                for(const pacientes of data){
                    // paciente.innerHTML += `<option id= "${pacientes.id}" value=" {
                    //     "nombre": "${pacientes.nombre}",
                    //     "apellido": "${pacientes.apellido}",
                    //     "dni": "${pacientes.dni}",
                    //     "fechaIngreso": "${pacientes.fechaIngreso}",
                    //     "domicilio": {
                    //         "calle":"${pacientes.calle}",
                    //         "numero": "${pacientes.numero}",
                    //         "localidad": "${pacientes.localidad}",
                    //         "provincia": "${pacientes.provincia}"
                    //     }
                    // }">${pacientes.nombre}</option>`
                    const pacienteOption = document.createElement('option');
                    pacienteOption.value = pacientes.id;
                    pacienteOption.textContent = pacientes.nombre;
                    selectPaciente.appendChild(pacienteOption);
                }
                
                console.log(data);
                console.log("paciente")
                console.log(selectPaciente);
            }).catch(function(error) {
                // Manejo de errores
                console.error(error);
            });

            selectPaciente.removeEventListener('click', arguments.callee);
    })


    selectOdontologo.addEventListener('click',function(event){
        const rawResponse = fetch('odontologos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });
    
            rawResponse.then(function(response) {
                return response.json();
            }).then(function(data) {
                // Aquí puedes trabajar con los datos en formato JSON
                for(const odontologos of data){
                    // odontologo.innerHTML += `<option id= "${odontologos.id}" value=" {
                    //     "matricula": "${odontologos.matricula}",
                    //     "nombre": "${odontologos.nombre}",
                    //     "apellido": "${odontologos.apellido}"
                    // }">${odontologos.nombre}</option>`
                    const odontologoOption = document.createElement('option');
                    odontologoOption.value = odontologos.id;
                    odontologoOption.textContent = odontologos.nombre;
                    selectOdontologo.appendChild(odontologoOption);
                }
                console.log(document.querySelector("option").value);

                
                console.log(data);
            }).catch(function(error) {
                // Manejo de errores
                console.error(error);
            });

            selectOdontologo.removeEventListener('click', arguments.callee);
    })

    // Función para obtener el objeto completo por su ID
    function obtenerObjetoPorIdPaciente(id) {
    // Realizar la solicitud para obtener el objeto completo utilizando el ID
    const rawResponse = fetch(`pacientes/${id}`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    },
    });

    return rawResponse
    .then(function(response) {
        return response.json();
    })
    .then(function(data) {
        return data;
    })
    .catch(function(error) {
        console.error(error);
        return null;
    });
}

    // Función para obtener el objeto completo por su ID
    function obtenerObjetoPorIdOdontologo(id) {
        // Realizar la solicitud para obtener el objeto completo utilizando el ID
        const rawResponse = fetch(`odontologos/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        });
    
        return rawResponse
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            return data;
        })
        .catch(function(error) {
            console.error(error);
            return null;
        });
    }
    
    // REGISTRAR TURNO

    form.addEventListener('submit', async function (event) {

        event.preventDefault();

        const pacienteId = selectPaciente.options[selectPaciente.selectedIndex].value;
        const odontologoId = selectOdontologo.options[selectOdontologo.selectedIndex].value;
        const fechaHoraValue = fechaHora.value;
        console.log("que tiene esta variable")
        console.log(pacienteId);

        // Obtener el objeto completo del paciente y el odontólogo utilizando los IDs
        const pacienteObjeto = await obtenerObjetoPorIdPaciente(pacienteId);
        const odontologoObjeto = await obtenerObjetoPorIdOdontologo(odontologoId);

        console.log("me trae el objeto?")
        console.log(pacienteObjeto)

        if (pacienteObjeto && odontologoObjeto){
            const payload = {
                paciente: pacienteObjeto,
                odontologo: odontologoObjeto,
                fechaYHora: fechaHoraValue
            }
            console.log("aca")
            console.log(payload)
    
            const rawResponse = await fetch('turnos/registrar', {
                method: 'POST',
                headers:{
                            'Content-Type': 'application/json'
                        },
                body: JSON.stringify(payload)
            });
            console.log(rawResponse)
    
            alert("El turno fue registrado con exito!");
            window.location.href = "index.html";
        }else{
            console.error("No se pudo obtener el objeto completo del paciente o del odontólogo");
        }
        

        

    });

    /* ACTUALIZAR TURNO */

    const formEdit = document.querySelector("#formularioTEdit")
    const selectPacienteEdit= document.querySelector("#pacienteTEdit")
    const selectOdontologoEdit = document.querySelector("#odontologoTEdit")
    const fechaHoraEdit = document.querySelector("#fechaHoraEdit")

    selectPacienteEdit.addEventListener('click',function(event){
        const rawResponse = fetch('pacientes', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });
    
            rawResponse.then(function(response) {
                return response.json();
            }).then(function(data) {
                // Aquí puedes trabajar con los datos en formato JSON
                for(const pacientes of data){
                    const pacienteOption = document.createElement('option');
                    pacienteOption.value = pacientes.id;
                    pacienteOption.textContent = pacientes.nombre;
                    selectPacienteEdit.appendChild(pacienteOption);
                }
                
                console.log(data);
                console.log("paciente")
                console.log(selectPaciente);
            }).catch(function(error) {
                // Manejo de errores
                console.error(error);
            });

            selectPacienteEdit.removeEventListener('click', arguments.callee);
    })

    selectOdontologoEdit.addEventListener('click',function(event){
        const rawResponse = fetch('odontologos', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });
    
            rawResponse.then(function(response) {
                return response.json();
            }).then(function(data) {
                // Aquí puedes trabajar con los datos en formato JSON
                for(const odontologos of data){
                    const odontologoOption = document.createElement('option');
                    odontologoOption.value = odontologos.id;
                    odontologoOption.textContent = odontologos.nombre;
                    selectOdontologoEdit.appendChild(odontologoOption);
                }
                console.log(document.querySelector("option").value);

                
                console.log(data);
            }).catch(function(error) {
                // Manejo de errores
                console.error(error);
            });

            selectOdontologoEdit.removeEventListener('click', arguments.callee);
    })

    formEdit.addEventListener('submit', async function (event) {

        event.preventDefault();

        const idEdit= document.querySelector("#idT").value
        const pacienteId = selectPacienteEdit.options[selectPacienteEdit.selectedIndex].value;
        const odontologoId = selectOdontologoEdit.options[selectOdontologoEdit.selectedIndex].value;
        const fechaHoraValue = fechaHoraEdit.value;
        console.log("que tiene esta variable")
        console.log(pacienteId);

        // Obtener el objeto completo del paciente y el odontólogo utilizando los ID
        const pacienteObjeto = await obtenerObjetoPorIdPaciente(pacienteId);
        const odontologoObjeto = await obtenerObjetoPorIdOdontologo(odontologoId);

        console.log("me trae el objeto?")
        console.log(pacienteObjeto)

        if (pacienteObjeto && odontologoObjeto){
            const payload = {
                id: idEdit,
                paciente: pacienteObjeto,
                odontologo: odontologoObjeto,
                fechaYHora: fechaHoraValue
            }
            console.log("aca")
            console.log(payload)
    
            const rawResponse = await fetch('turnos/actualizar', {
                method: 'PUT',
                headers:{
                            'Content-Type': 'application/json'
                        },
                body: JSON.stringify(payload)
            });
            console.log(rawResponse)
    
            alert("El turno fue actualizado con exito!");
            window.location.href = "index.html";
        }else{
            console.error("No se pudo obtener el objeto completo del paciente o del odontólogo");
        }
        //window.location.href = "index.html";

        

    });


    //ELIMINAR TURNO 
    
    const formDelete = document.querySelector("#formularioDelete")
    formDelete.addEventListener('submit', function (event) {

        const idDelete= document.querySelector("#idDelete").value;

        event.preventDefault()

        const rawResponse = fetch('turnos/eliminar/' + idDelete, {
            method: 'DELETE',
            headers:{
                        'Content-Type': 'application/json'
                    },
        });
        console.log(rawResponse)

        alert("El turno fue eliminado con exito!");
        window.location.href = "index.html";

        

    });


})