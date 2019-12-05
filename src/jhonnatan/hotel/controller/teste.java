/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jhonnatan
 */
public class teste {

    public teste() throws JRException {
    
        List<Funcionario> lfunc = new ArrayList<>();
        Funcionario f = new Funcionario();
        f.setNome("Jhonny");
        f.setSobrenome("Bezerra");
        lfunc.add(f);
        f.setNome("dasdfgsa");
        f.setSobrenome("asfhjk");
        lfunc.add(f);

        InputStream stream = getClass().getResourceAsStream("hello.jrxml");
        System.out.println(stream);
        JasperReport report = JasperCompileManager.compileReport(stream);
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lfunc));
        JasperViewer.viewReport(print, false);
    }

    
    
    public static void main(String[] args) throws JRException {
        new teste();
    }
    
}
