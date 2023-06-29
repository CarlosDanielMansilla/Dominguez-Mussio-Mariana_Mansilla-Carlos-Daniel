window.addEventListener('DOMContentLoaded', function(){

    const volver= document.querySelector(".btnVolver")
    volver.addEventListener('click',function(){
        window.location.href = "index.html";
    })

    const pacienteHtml = document.querySelector(".listarPaciente")
    const btn = document.querySelector(".btn-listar")
    const btnBuscar = document.querySelector("#formularioBuscar button")
    let listaGenerada = false;
    btn.addEventListener('click',function(event){
        
        event.preventDefault()

        if (!listaGenerada) {
            const rawResponse = fetch('pacientes', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            });

        console.log(rawResponse)

        rawResponse.then(function(response) {
            return response.json();
        }).then(function(data) {
            pacienteHtml.innerHTML="";
            // Aquí puedes trabajar con los datos en formato JSON
            for(const paciente of data){
                pacienteHtml.innerHTML += `<article id="artList">
                <h2>Paciente</h2>
                <h3>Nombre: `+ paciente.nombre +`</h3>
                <h3>Apellido: `+ paciente.apellido +`</h3>
                <h3>DNI: `+ paciente.dni +`</h3>
                <h3>Fecha de Ingreso: `+ paciente.fechaIngreso +`</h3>
                <h2>Domicilio</h2>
                <h3>Calle: `+ paciente.domicilioDto.calle +`</h3>
                <h3>Numero: `+ paciente.domicilioDto.numero +`</h3>
                <h3>Localidad: `+ paciente.domicilioDto.localidad +`</h3>
                <h3>Provincia: `+ paciente.domicilioDto.provincia +`</h3>
    
                </article>`
            }
            
            console.log(data);
        }).catch(function(error) {
            // Manejo de errores
            console.error(error);
        });
        //const listadoPaciente = rawResponse.json();
        
        listaGenerada = true;
        }
    })


    btnBuscar.addEventListener('click',function(event){
        
        event.preventDefault()
        const idBuscar= document.querySelector("#idBuscar").value;

        
        const rawResponse = fetch('pacientes/'+idBuscar, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        });

        console.log(rawResponse)

        rawResponse.then(function(response) {
            return response.json();
        }).then(function(data) {
            // Aquí puedes trabajar con los datos en formato JSON
            
            pacienteHtml.innerHTML = `<article id="artList">
            <h2>Paciente</h2>
            <h3>Nombre: `+ data.nombre +`</h3>
            <h3>Apellido: `+ data.apellido +`</h3>
            <h3>DNI: `+ data.dni +`</h3>
            <h3>Fecha de Ingreso: `+ data.fechaIngreso +`</h3>
            <h2>Domicilio</h2>
            <h3>Calle: `+ data.domicilioDto.calle +`</h3>
            <h3>Numero: `+ data.domicilioDto.numero +`</h3>
            <h3>Localidad: `+ data.domicilioDto.localidad +`</h3>
            <h3>Provincia: `+ data.domicilioDto.provincia +`</h3>

            </article>`
            
            console.log(data);
        }).catch(function(error) {
            // Manejo de errores
            console.error(error);
        });
        //const listadoPaciente = rawResponse.json();
        listaGenerada = false;
        
    })

})