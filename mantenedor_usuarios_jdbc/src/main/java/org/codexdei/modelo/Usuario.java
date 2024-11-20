package org.codexdei.modelo;

public class Usuario {

    private Long idUsuario;
    private String nombreUsuario;
    private String password;
    private String email;

    public Usuario(){}

    public Long getIdUsuario(){
        return this.idUsuario;
    }
    public void setIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "id_usuario=" + idUsuario + " || " +
                "Usuario=" + nombreUsuario + " || " +
                "Password=" + password + " || " +
                "Email=" + email;
    }
}
