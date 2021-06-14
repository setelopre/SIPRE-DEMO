/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataService.Despachadores.Impl;

import BusinessServices.Beans.BeanComun;
import DataService.Despachadores.CombosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public class CombosDAOImpl implements CombosDAO {

    private final Connection objConnection;
    private ResultSet objResultSet;
    private PreparedStatement objPreparedStatement;
    private String sql;
    private List lista;
    private BeanComun comun;

    public CombosDAOImpl(Connection objConnection1) {
        objConnection = objConnection1;
    }

    //CONSULTAS GENERALES
    @Override
    public List getPeriodo() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA PERIODO POR ORDEN DESCENDENTES Y LO
         * ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CPERIODO_CODIGO AS CODIGO "
                + "FROM SIPRE_PERIODO WHERE "
                + "CESTADO_CODIGO='AC' "
                + "ORDER BY CODIGO DESC";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPeriodo() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getMes() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA MESES POR ORDEN ASCENDENTE Y
         * LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CMES_CODIGO AS CODIGO, VMES_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPRE_MESES "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getMes() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getAreaLaboral() {
        lista = new LinkedList<>();
        /*
         * OBTENEMOS LOS DATOS DE LA TABLA AREA LABORAL
         * Y LO ALMACENAMOS EN UNA LISTA
         */
        sql = "SELECT CAREA_LABORAL_CODIGO AS CODIGO, VAREA_LABORAL_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPRE_AREA_LABORAL "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreaLaboral() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    //UBIGEO
    @Override
    public List getDepartamento() {
        lista = new LinkedList<>();
        sql = "SELECT CODDPT AS CODIGO, NOMDPT AS DESCRIPCION "
                + "FROM TABDPT "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDepartamento() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getProvincia(String departamento) {
        lista = new LinkedList<>();
        sql = "SELECT CODPRA AS CODIGO, NOMPRA AS DESCRIPCION "
                + "FROM TABPRA WHERE "
                + "CODDPT=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, departamento);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getProvincia(departamento) " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getDistrito(String departamento, String provincia) {
        lista = new LinkedList<>();
        sql = "SELECT CODUBI AS CODIGO, DESUBI AS DESCRIPCION "
                + "FROM TABUBI WHERE "
                + "CODDPT=? AND "
                + "CODPRA=? "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, departamento);
            objPreparedStatement.setString(2, provincia);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDistrito(departamento, provincia) " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    //MESA DE PARTES
    @Override
    public List getInstitucion() {
        lista = new LinkedList<>();
        sql = "SELECT VINSTITUCION_ABREVIATURA AS DESCRIPCION, CINSTITUCION_CODIGO AS CODIGO "
                + "FROM SIPE_INSTITUCION WHERE "
                + "CORGANISMO_CODIGO = '01'  "
                + "ORDER BY DESCRIPCION ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getInstitucion() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getAreaMesaPartes() {
        lista = new LinkedList<>();
        sql = "SELECT COARLB AS CODIGO, "
                + "UTIL_NEW.FUN_NOMBRE_AREA_LABORAL(COARLB) AS DESCRIPCION "
                + "FROM TABUSU WHERE "
                + "COUUOO='0003' AND ESTREG='AC' AND "
                + "USUTROPA='N' "
                + "GROUP BY COARLB "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getAreaMesaPartes() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getUsuarioMesaPartes(String area) {
        lista = new LinkedList<>();
        sql = "SELECT CODUSU AS CODIGO,"
                + "NOMUSU||' '||APEUSU AS DESCRIPCION "
                + "FROM TABUSU WHERE "
                + "COUUOO='0003' AND ESTREG='AC' AND "
                + "DIPPTO='S' AND "
                + "COARLB='" + area + "' "
                + "ORDER BY DESCRIPCION ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioMesaPartes(String area) " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getPrioridad() {
        lista = new LinkedList<>();
        sql = "SELECT CPRIORIDAD_CODIGO AS CODIGO, "
                + "VPRIORIDAD_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPE_PRIORIDAD";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getPrioridad() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getTipoDocumentos() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DOCUMENTO_CODIGO AS CODIGO, VTIPO_DOCUMENTO_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPE_TIPO_DOCUMENTO "
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getTipoDocumentos() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getClasificacionDocumento() {
        lista = new LinkedList<>();
        sql = "SELECT CCLASIFICACION_DOCUMENTO_CODIG AS CODIGO, "
                + "VCLASIFICACION_DOCUMENTO_DESCR AS DESCRIPCION "
                + "FROM SIPE_CLASIFICACION_DOCUMENTO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getClasificacionDocumento() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getTipoDecreto() {
        lista = new LinkedList<>();
        sql = "SELECT NTIPO_DECRETO_CODIGO AS CODIGO, "
                + "VTIPO_DECRETO_DESCRIPCION AS DESCRIPCION "
                + "FROM SIPRE_TIPO_DECRETO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getClasificacionDocumento() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getDocumentoReferencia(String periodo, String tipo) {
        lista = new LinkedList<>();
        String add = "";
        if (tipo.equals("I")) {
            add = " CDOCUMENTO_ESTADO='DE' ";
        } else {
            add = " CDOCUMENTO_ESTADO  IN ('DE','RE') ";
        }
        sql = "SELECT CDOCUMENTO_NUMERO AS CODIGO,"
                + "PK_MESA_PARTES.FUN_NOMBRE_TIPO_DOCUMENTO(NTIPO_DOCUMENTO_CODIGO)||'-'||CDOCUMENTO_NRO_DOCUMENTO AS DESCRIPCION "
                + "FROM SIPE_DOCUMENTO WHERE "
                + "CPERIODO_CODIGO=? AND "
                + "CDOCUMENTO_TIPO='E' AND "
                + add
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentoReferencia() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getUsuarioDocumento(String usuario) {
        lista = new LinkedList<>();
        sql = "SELECT CODUSU AS CODIGO, NOMUSU||' '||APEUSU AS USUARIO FROM TABUSU "
                + "WHERE COUUOO='0003' AND DIPPTO='S' AND ESTREG='AC' "
                + "AND CODUSU=? "
                + "ORDER BY USUARIO ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("USUARIO"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioDocumento(usuario) " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getDocumentoReferencia(String periodo, String tipo, String usuario) {
        lista = new LinkedList<>();
        String add = "";
        if (tipo.equals("I")) {
            add = " DD.CESTADO_DOCUMENTO IN ('RE') ";
        } else {
            add = " DD.CESTADO_DOCUMENTO  IN ('DE','RE','RS') ";
        }
        sql = "SELECT D.CDOCUMENTO_NUMERO AS CODIGO,"
                + "PK_MESA_PARTES.FUN_NOMBRE_TIPO_DOCUMENTO(D.NTIPO_DOCUMENTO_CODIGO)||'-'||TRIM(D.CDOCUMENTO_NRO_DOCUMENTO)||' '||TRIM(VDOCUMENTO_ASUNTO) AS DESCRIPCION "
                + "FROM SIPE_DOCUMENTO D INNER JOIN SIPE_DECRETO_DOCUMENTO DD ON"
                + "(D.CPERIODO_CODIGO=DD.CPERIODO_CODIGO AND "
                + "D.CDOCUMENTO_NUMERO=DD.CDOCUMENTO_NUMERO) "
                + " WHERE "
                + "D.CPERIODO_CODIGO=? AND "
                + "DD.VUSUARIO_RECEPCION=? AND "
                + "D.CDOCUMENTO_TIPO='E' AND "
                + add
                + "ORDER BY DESCRIPCION";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setString(2, usuario);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getDocumentoReferencia() " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getUsuarioJefatura(String periodo) {
        lista = new LinkedList<>();
        sql = "SELECT CODSIS AS CODIGO, APEPRS||' '||NOMPRS AS DESCRIPCION "
                + "FROM TABPRS WHERE "
                + "CODPER=? AND "
                + "COARLB='01'";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioJefatura(" + periodo + ") " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getUsuarioSubDecreto(String area, String codusu) {
        lista = new LinkedList<>();
        sql = "SELECT CODUSU AS CODIGO,"
                + "NOMUSU||' '||APEUSU AS DESCRIPCION "
                + "FROM TABUSU WHERE "
                + "COUUOO='0003' AND ESTREG='AC' AND "
                + "USUTROPA='N' AND "
                + "COARLB='" + area + "' AND "
                + "CODUSU NOT IN (?)"
                + "ORDER BY DESCRIPCION ";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, codusu);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getUsuarioSubDecreto(String area) " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

    @Override
    public List getGenericaGastoUnidad(String periodo, Integer presupuesto, String unidadOperativa) {
        lista = new LinkedList<>();
        sql = "SELECT TRIM(CODGEN) AS CODIGO, TRIM(CODGEN)||':'||UTIL_NEW.FUN_NOMGEN(TRIM(CODGEN)) AS DESCRIPCION "
                + "FROM MODEPA WHERE "
                + "CODPER=? AND "
                + "COPPTO=? AND "
                + "COUUOO=? "
                + "GROUP BY TRIM(CODGEN) "
                + "UNION ALL "
                + "SELECT DISTINCT '*' AS CODIGO, 'TODAS LAS GENERICAS' AS DESCRIPCION "
                + "FROM DUAL "
                + "ORDER BY CODIGO";
        try {
            objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, periodo);
            objPreparedStatement.setInt(2, presupuesto);
            objPreparedStatement.setString(3, unidadOperativa);
            objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                comun = new BeanComun();
                comun.setCodigo(objResultSet.getString("CODIGO"));
                comun.setDescripcion(objResultSet.getString("DESCRIPCION"));
                lista.add(comun);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener getGenericaGastoUnidad(" + periodo + ", " + presupuesto + ", " + unidadOperativa + ") " + e.getMessage());
        } finally {
            try {
                if (objResultSet != null) {
                    objResultSet.close();
                    objPreparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }

}
