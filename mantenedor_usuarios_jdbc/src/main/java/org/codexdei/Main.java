package org.codexdei;

import org.codexdei.Repositorio.Repositorio;
import org.codexdei.Repositorio.UsuarioRepositorioImpl;
import org.codexdei.modelo.Usuario;

import javax.swing.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Main {

    public static boolean estadoPrograma = true;

    public static void main(String[] args) {

        do {

            try{

                Repositorio repousuario = new UsuarioRepositorioImpl();

                Map<String, Integer> operaciones = new HashMap();
                operaciones.put("Actualizar", 1);
                operaciones.put("Eliminar", 2);
                operaciones.put("Agregar", 3);
                operaciones.put("Listar", 4);
                operaciones.put("Buscar",5);
                operaciones.put("Salir", 6);

                Object[] opArreglo = operaciones.keySet().toArray();

                Object opcion = JOptionPane.showInputDialog(null,
                        "Seleccione un Operación",
                        "Mantenedor de Usuarios",
                        JOptionPane.INFORMATION_MESSAGE, null, opArreglo, opArreglo[0]);

                if (opcion == null) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
                } else {
                    Integer opcionIndice = operaciones.get(opcion.toString());

                    switch (opcionIndice){

                        case 1 -> {

                            Usuario us = new Usuario();

                            us.setIdUsuario(Long.valueOf(JOptionPane.showInputDialog("Ingrese el numero de ID que desea actualizar")));
                            us.setNombreUsuario(JOptionPane.showInputDialog("Ingrese el nombre del usuario actualizar"));
                            us.setPassword(JOptionPane.showInputDialog("Ingrese el password a actualizar"));
                            us.setEmail(JOptionPane.showInputDialog("Ingrese el email a actualizar"));
                            repousuario.actualizar(us);
                        }

                        case 2 -> {

                            Long idEliminar = Long.valueOf(JOptionPane.showInputDialog("Ingrese el numero de ID que desea Eliminar"));
                            StringBuilder mensaje = new StringBuilder();
                            mensaje.append("El registro a eliminar seria: ").append(repousuario.buscarId(idEliminar));

                            String mensajeCuadroDialogo = "Estas seguro deseas eliminar el registro?: ";

                            Object[] opciones = {"SI", "NO"};

                            int respuesta = JOptionPane.showOptionDialog(
                                    null,
                                    mensaje,
                                    "CONFIRMACION",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    opciones,
                                    opciones[0]
                            );

                            switch (respuesta){

                                case 0 -> {
                                    repousuario.eliminar(idEliminar);
                                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                                }
                                case 1 -> {
                                    JOptionPane.showMessageDialog(null, "NO se ha eliminado el registro");
                                }
                            }
                        }//case 2 del switch principal
                        case 3 -> {

                            boolean estadometodo = true;

                                Usuario us2 = new Usuario();

                                us2.setNombreUsuario(JOptionPane.showInputDialog("Ingrese el nombre del usuario agregar"));
                                us2.setPassword(JOptionPane.showInputDialog("Ingrese el password a actualizar"));
                                us2.setEmail(JOptionPane.showInputDialog("Ingrese el email a actualizar"));
                                repousuario.actualizar(us2);
                        }
                        case 4 -> {

                            System.out.println("******************* LISTA DE USUARIOS ********************");
                            repousuario.listar().forEach(System.out::println);
                        }
                        case 5 -> {

                            StringBuilder mensajeBuscar = new StringBuilder("El registro encontrado es:");
                            Long idBuscar = Long.valueOf(JOptionPane.showInputDialog("Ingrese el ID a buscar en la base de datos:"));
                            mensajeBuscar.append(repousuario.buscarId(idBuscar));
                            JOptionPane.showMessageDialog(null,mensajeBuscar,
                                    "Usuario buscado",JOptionPane.INFORMATION_MESSAGE);
                        }

                        case 6 -> {

                            estadoPrograma = false;
                        }
                    }//switch Principal
                }
            }catch (InputMismatchException e){

                JOptionPane.showMessageDialog(null,
                        "El valor ingresado es invalido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();

            }catch (Exception e){

                JOptionPane.showMessageDialog(null,
                        "Error en la ejecucion: " + e.getMessage(),"ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        }while (estadoPrograma);
    }
}
