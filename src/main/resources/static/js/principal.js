window.addEventListener('load', function(){

    const paciente = document.querySelector("#cuadroPaciente")
    paciente.addEventListener('click',function(){
        window.location.href = "paciente.html";
    })

    const odontologo = document.querySelector("#cuadroOdontologo")
    odontologo.addEventListener('click',function(){
        window.location.href = "odontologo.html";
    })

    const turno = document.querySelector("#cuadroTurno")
    turno.addEventListener('click',function(){
        window.location.href = "turno.html";
    })

    const listPaciente= document.querySelector(".btnPaciente")
    listPaciente.addEventListener('click',function(){
        window.location.href = "listarPaciente.html";
    })

    const listOdontologo= document.querySelector(".btnOdontologo")
    listOdontologo.addEventListener('click',function(){
        window.location.href = "listarPaciente.html";
    })

    const listTurno= document.querySelector(".btnTurno")
    listTurno.addEventListener('click',function(){
        window.location.href = "listarPaciente.html";
    })
})