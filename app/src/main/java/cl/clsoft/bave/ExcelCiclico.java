package cl.clsoft.bave;

import android.os.Environment;
import android.util.Log;
import cl.clsoft.bave.apis.IRestHomologacion;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;

public class ExcelCiclico {
    private static final String TAG = "AdapterCsvToXls";
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;
    private static String Organizacion;
    private static String Date;
    private static String Items;
    private static String Responsable;


    //API
    IRestHomologacion iRestHomologacion;

    public void leerArchivo(String nombreArchivo , String  nombreArchivoSin) {
        try {
            Log.d(TAG, "ADAPTERCSV");
            System.out.println("LeerArchivo :  "+nombreArchivo);
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File ruta = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File[] listFile = ruta.listFiles();
            File rutaAxuliar = null;


            Workbook wb = new HSSFWorkbook();




            Cell cell = null;
            Cell cellT = null;
            Cell cellC = null;
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setWrapText(true);

            CellStyle cellStyleT = wb.createCellStyle();
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            CellStyle cellStyleCuerpo = wb.createCellStyle();

            cellStyleCuerpo.setBorderLeft(BorderStyle.THIN);
            cellStyleCuerpo.setBorderBottom(BorderStyle.THIN);
            cellStyleCuerpo.setBorderRight(BorderStyle.THIN);




            cellStyleCuerpo.setWrapText(true);
            cellStyleT.setWrapText(true);
            cellStyle.setWrapText(true);




            cellStyle.setBorderLeft(BorderStyle.THICK);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THICK);
            cellStyle.setBorderTop(BorderStyle.THICK);



            Sheet sheet = null;
            sheet = wb.createSheet("Conteo Cliclico");




            //TITULO
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 20);
            font.setFontName("Courier New");
            font.setItalic(true);
            // Applying font to the style


            cellStyleT.setFont(font);
            cellStyleT.setAlignment(HorizontalAlignment.CENTER);

            Row row = null;
            Row rowT = null;
            Row rowCuerpo = null;
            Row rowSub = null;

            rowT = sheet.createRow(0);
            cellT = rowT.createCell(4);
            cellT.setCellValue("CONTEO CICLICO");
            cellT.setCellStyle(cellStyleT);
            sheet.addMergedRegion(new CellRangeAddress(0,1,4,9));
            //sheet.autoSizeColumn(8,true);


            //SUBTITULOS



            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            row = null;

            row = sheet.createRow(7);
            cell = row.createCell((short)1);
            cell.setCellValue("N°");
            cell.setCellStyle(cellStyle);

            sheet.createRow(8);
            cell = row.createCell(2);
            cell.setCellValue("CODIGO SIGLE");
            cell.setCellStyle(cellStyle);
            sheet.createRow(18);
            cell = row.createCell(3);
            cell.setCellValue("CODIGO DE BARRAS");
            cell.setCellStyle(cellStyle);

            sheet.createRow(9);
            cell = row.createCell(4);
            cell.setCellValue("DESCRIPCION DEL ARTICULO");
            cell.setCellStyle(cellStyle);

            sheet.createRow(10);
            cell = row.createCell(5);
            cell.setCellValue("UDM");
            cell.setCellStyle(cellStyle);

            sheet.createRow(11);
            cell = row.createCell(6);
            cell.setCellValue("SUBINVENTARIO/ALMACEN");
            cell.setCellStyle(cellStyle);

            sheet.createRow(12);
            cell = row.createCell(7);
            cell.setCellValue("LOCALIZADOR");
            cell.setCellStyle(cellStyle);

            sheet.createRow(13);
            cell = row.createCell(8);
            cell.setCellValue("LOTE SISTEMA");
            cell.setCellStyle(cellStyle);

            sheet.createRow(14);
            cell = row.createCell(9);
            cell.setCellValue("N°SERIE");
            cell.setCellStyle(cellStyle);

            sheet.createRow(15);
            cell = row.createCell(10);
            cell.setCellValue("CANTIDAD SIGLE");
            cell.setCellStyle(cellStyle);


            sheet.createRow(16);
            cell = row.createCell(11);
            cell.setCellValue("CANTIDAD TOMADA");
            cell.setCellStyle(cellStyle);

            sheet.createRow(17);
            cell = row.createCell(12);
            cell.setCellValue("DIFERENCIAS");
            cell.setCellStyle(cellStyle);



            //SUBTITULOS
            Cell cellSub = null;
            CellStyle cellStyleSub = wb.createCellStyle();

            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


            Font fontSub = wb.createFont();
            fontSub.setFontHeightInPoints((short) 6);
            fontSub.setBold(true);
            fontSub.setItalic(false);

            cellStyleSub.setFont(fontSub);

            // Applying font to the style
/*
            rowSub = sheet.createRow(2);
            sheet.createRow(2);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("CODIGO ORGANIZACION : "+partes[0]);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));

            rowSub = sheet.createRow(3);
            sheet.createRow(3);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("CANTIDAD DE ARTÍCULOS DE MUESTRA: ");
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(3,3,1,3));

            rowSub = sheet.createRow(4);
            sheet.createRow(4);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("FECHA DE MUESTRA:"+partes[3]);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));


            rowSub = sheet.createRow(5);
            sheet.createRow(5);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("RESPONSABLE: "+partes[22]);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));

*/


            for (int i = 0; i < listFile.length; i++) {
                if(listFile[i].getName().equals(nombreArchivo)){
                    rutaAxuliar = listFile[i];
                }

            }
            int aux = 1;

            lector = new BufferedReader(new FileReader(rutaAxuliar));


            while ((linea = lector.readLine()) != null) {
                partes = linea.split(";");



                rowCuerpo = sheet.createRow(aux+7);
                for (int i = 0; i < partes.length; i++) {

                   /* System.out.print(partes[i] + "  |  ");
                    cellC = rowCuerpo.createCell(i+1);
                    cellC.setCellValue(partes[i]);
                    cellStyle.setBorderLeft(BorderStyle.THICK);
                    cellStyle.setBorderRight(BorderStyle.THICK);
                    cellStyle.setBorderTop(BorderStyle.THICK);
                    cellStyle.setBorderBottom(BorderStyle.THICK);
                    cellC.setCellStyle(cellStyleCuerpo);*/
                    Organizacion = partes[0];
                    Date = partes[3];
                    Responsable = partes[22];
                    System.out.print(partes[i] +";");


                    if(i == 2){
                        //CODIGO SIGLE
                        cellC = rowCuerpo.createCell(1);
                        cellC.setCellValue( aux);
                        cellC.setCellStyle(cellStyleCuerpo);

                        cellC = rowCuerpo.createCell(2);
                        cellC.setCellValue( partes[11]);
                        cellC.setCellStyle(cellStyleCuerpo);
                    }
                    if(i == 3){
                        //CODIGO DE BARRAS
                        cellC = rowCuerpo.createCell(3);
                        cellC.setCellValue( " ");
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 4){
                        //DESCRIPCION ARTICULO
                        cellC = rowCuerpo.createCell(4);
                        cellC.setCellValue( partes[12]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 5){
                        //UNIDAD DE MEDIDA
                        cellC = rowCuerpo.createCell(5);
                        cellC.setCellValue( partes[19]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 6){
                        //SUB INVENTARIO
                        cellC = rowCuerpo.createCell(6);
                        cellC.setCellValue( partes[13]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 7){
                        //LOCALIZADOR
                        cellC = rowCuerpo.createCell(7);
                        cellC.setCellValue( partes[15]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 8){
                        //LOTE SISTEMA
                        cellC = rowCuerpo.createCell(8);
                        cellC.setCellValue( partes[17]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 9){
                        //NUMERO DE SERIE
                        cellC = rowCuerpo.createCell(9);
                        cellC.setCellValue( partes[17]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 10){
                        //CANTIDAD SIGLE
                        cellC = rowCuerpo.createCell(10);
                        cellC.setCellValue(partes[18]);
                        cellC.setCellStyle(cellStyleCuerpo);

                    }
                    if(i == 11){
                        //CANTIDAD TOMADA
                        cellC = rowCuerpo.createCell(11);
                        cellC.setCellValue( partes[20]);
                        cellC.setCellStyle(cellStyleCuerpo);


                    }
                    if(i == 12){
                        //DIFERENCIAS
                        String tomada = partes[20];
                        String Original = partes[19];
                        cellC = rowCuerpo.createCell(12);
                        cellC.setCellValue( "OPERAR");
                        cellC.setCellStyle(cellStyleCuerpo);

                    }

                }
                System.out.println(" ");
                System.out.print(aux);
                aux++;
                //imprimirLinea();
                System.out.println();
            }



            rowSub = sheet.createRow(2);
            sheet.createRow(2);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("CODIGO ORGANIZACION : "+Organizacion);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));


            int itemCount = aux -1 ;
            rowSub = sheet.createRow(3);
            sheet.createRow(3);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("CANTIDAD DE ARTÍCULOS DE MUESTRA: "+ itemCount );
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(3,3,1,3));

            rowSub = sheet.createRow(4);
            sheet.createRow(4);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("FECHA DE MUESTRA: "+ Date);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));


            rowSub = sheet.createRow(5);
            sheet.createRow(5);
            cellSub = rowSub.createCell(1);
            cellSub.setCellValue("RESPONSABLE: " +Responsable);
            cellSub.setCellStyle(cellStyleSub);
            sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));



            lector.close();
            linea = null;
            partes = null;

            FileOutputStream outputStream = null;
            File Dir = new File(tarjetaSD.getAbsolutePath(), "inbound");
            File rutaArchivo = new File(Dir,  nombreArchivoSin+".xls");
            Log.d(TAG, "Creando xls");

            try {
                // outputStream = new FileOutputStream(file);
                outputStream = new FileOutputStream(rutaArchivo);
                wb.write(outputStream);

            } catch (IOException e) {
                e.printStackTrace();


                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void imprimirLinea() {
        for (int i = 0; i < partes.length; i++) {
            System.out.print(partes[i] + "  |  ");
        }
    }

}

