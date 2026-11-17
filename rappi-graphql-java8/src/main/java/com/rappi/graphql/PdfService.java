package com.rappi.graphql;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Servicio que genera reportes basicos en formato PDF (OpenPDF).
 */
@Service
public class PdfService {

    private static final Font TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
    private static final Font NEGRITA = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
    private static final Font NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 10);

    /**
     * Genera el reporte de pedidos del dia.
     * @param pedidos pedidos a incluir
     * @return PDF en Base64
     */
    public String reporteDiario(List<Pedido> pedidos) {
        return generar(doc -> {
            doc.add(new Paragraph("Reporte de pedidos del dia - " + LocalDate.now(), TITULO));
            doc.add(new Paragraph(" "));
            PdfPTable t = new PdfPTable(new float[]{1, 2, 3, 3, 2, 2});
            t.setWidthPercentage(100);
            for (String h : new String[]{"ID", "Cedula", "Cliente", "Descripcion", "Estado", "Total"}) {
                t.addCell(new Phrase(h, NEGRITA));
            }
            double suma = 0;
            for (Pedido p : pedidos) {
                t.addCell(new Phrase(String.valueOf(p.getId()), NORMAL));
                t.addCell(new Phrase(p.getCedulaCliente(), NORMAL));
                t.addCell(new Phrase(p.getNombreCliente(), NORMAL));
                t.addCell(new Phrase(p.getDescripcion(), NORMAL));
                t.addCell(new Phrase(p.getEstado(), NORMAL));
                t.addCell(new Phrase(String.format("$%,.0f", p.getTotal()), NORMAL));
                suma += p.getTotal();
            }
            doc.add(t);
            doc.add(new Paragraph(" "));
            Paragraph resumen = new Paragraph("Pedidos: " + pedidos.size()
                    + "   |   Total vendido: " + String.format("$%,.0f", suma), NEGRITA);
            resumen.setAlignment(Element.ALIGN_RIGHT);
            doc.add(resumen);
        });
    }

    /**
     * Genera el reporte de un solo pedido.
     * @param p pedido
     * @return PDF en Base64
     */
    public String reportePedido(Pedido p) {
        return generar(doc -> {
            doc.add(new Paragraph("Pedido #" + p.getId(), TITULO));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Cliente: " + p.getNombreCliente(), NORMAL));
            doc.add(new Paragraph("Cedula: " + p.getCedulaCliente(), NORMAL));
            doc.add(new Paragraph("Direccion: " + p.getDireccionEntrega(), NORMAL));
            doc.add(new Paragraph("Descripcion: " + p.getDescripcion(), NORMAL));
            doc.add(new Paragraph("Estado: " + p.getEstado(), NORMAL));
            doc.add(new Paragraph("Fecha: " + p.getFecha() + " " + p.getHora(), NORMAL));
            doc.add(new Paragraph("Total: " + String.format("$%,.0f", p.getTotal()), NEGRITA));
        });
    }

    /** Escribe el contenido de un documento. */
    private interface Contenido {
        void escribir(Document doc) throws Exception;
    }

    private String generar(Contenido contenido) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document doc = new Document(PageSize.A4);
            PdfWriter.getInstance(doc, out);
            doc.open();
            contenido.escribir(doc);
            doc.close();
            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Error generando el PDF", e);
        }
    }
}
