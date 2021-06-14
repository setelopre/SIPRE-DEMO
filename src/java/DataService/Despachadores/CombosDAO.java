/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataService.Despachadores;

import java.util.List;

/**
 *
 * @author H-URBINA-M
 */
public interface CombosDAO {

    //CONSULTAS GENERALES
    public List getPeriodo();

    public List getMes();

    public List getAreaLaboral();

    //UBIGEO
    public List getDepartamento();

    public List getProvincia(String departamento);

    public List getDistrito(String departamento, String provincia);

    //MESA DE PARTES
    public List getInstitucion();

    public List getAreaMesaPartes();

    public List getUsuarioMesaPartes(String area);

    public List getPrioridad();

    public List getTipoDocumentos();

    public List getClasificacionDocumento();

    public List getTipoDecreto();

    public List getDocumentoReferencia(String periodo, String tipo);

    public List getUsuarioDocumento(String usuario);

    public List getDocumentoReferencia(String periodo, String tipo, String usuario);

    public List getUsuarioJefatura(String periodo);
    
    public List getUsuarioSubDecreto(String area, String codusu);

    
}
