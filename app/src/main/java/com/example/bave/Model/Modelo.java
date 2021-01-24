package com.example.bave.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bave.ConexionSQLiteHelper;
import com.example.bave.entidades.MtlTransactionLotsIfaceDto;
import com.example.bave.entidades.RcvHeadersInterfaceDTO;
import com.example.bave.entidades.RcvTransactionsInterfaceDTO;
import com.example.bave.utilidades.Utilidades;

public class Modelo {

    public SQLiteDatabase getConn(Context context){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context,"bd",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        return db;
    }

    public int insertUsuario(Context context, RcvHeadersInterfaceDTO dto){
        int res = 0;

        String sql = "INSERT INTO "+ Utilidades.TABLA_RCV_HEADERS_INTERFACE +"(header_interface_id, receipt_source_code," +
                " transaction_type, last_update_date, last_updated_by, created_by, receipt_num, vendor_id," +
                " vendor_site_code, vendor_site_id, ship_to_organization_code, location_id, receiver_id, currency_code," +
                " conversion_rate_type, conversion_rate, conversion_rate_date, payment_terms_id, transaction_date, comments," +
                "org_id,processing_status_code, validation_flag, group_id) VALUES ('"+dto.getHeaderInterfaceId()+"','"+dto.getReceiptSourceCode()+"','"+dto.getTransactionType()+"','"+dto.getLastUpdateDate()+"'," +
                "'"+dto.getLastUpdateBy()+"','"+dto.getCreatedBy()+"','"+dto.getReceiptNum()+"','"+dto.getVendorId()+"'," +
                "'"+dto.getVendorSiteCode()+"','"+dto.getVendorSiteId()+"','"+dto.getShipToOrganizationCode()+"','"+dto.getLocationId()+"'," +
                "'"+dto.getReceiverId()+"','"+dto.getCurrencyCode()+"','"+dto.getConversionRateType()+"','"+dto.getConversionRate()+"'," +
                "'"+dto.getConversionRateDate()+"','"+dto.getPaymentTermsId()+"','"+dto.getTransactionDate()+"','"+dto.getComments()+"'," +
                "'"+dto.getOrgId()+"','"+dto.getProcessingStatusCode()+"','"+dto.getValidationFlag()+"','"+dto.getGroupId()+"')";

        SQLiteDatabase db = this.getConn(context);
        try {
            db.execSQL(sql);
            res = 1;
            db.close();
        }catch (Exception e){
            Log.d("Error Base",e.getMessage());
        }

        return res;
    }

    public int insertLineas(Context context, RcvTransactionsInterfaceDTO dto){
        int res = 0;

        String sql = "INSERT INTO "+Utilidades.TABLA_RCV_TRANSACTIONS_INTERFACE+"(interface_transaction_id, last_update_date,"+
                " last_updated_by, creation_date, created_by, transaction_type, transaction_date, quantity, unit_of_measure," +
                " item_id, item_description, uom_code, ship_to_location_id, primary_quantity," +
                " receipt_source_code, vendor_id, vendor_site_id, to_organization_id, po_header_id, po_line_id, po_line_location_id," +
                " po_unit_price, currency_code, source_document_code, currency_conversion_type, currency_conversion_rate, currency_conversion_date," +
                " po_distribution_id, destination_type_code, location_id, deliver_to_location_id, inspection_status_code, header_interface_id, vendor_site_code," +
                " processing_status_code, use_mtl_lot, use_mtl_serial, transaction_status_code, validation_flag, org_id, group_id, auto_transact_code) VALUES ('"+dto.getInterfaceTransactionId()+"','"+dto.getLastUpdateDate()+"'," +
                "'"+dto.getLastUpdatedBy()+"','"+dto.getCreationDate()+"','"+dto.getCreatedBy()+"','"+dto.getTransactionType()+"'," +
                "'"+dto.getTransactionDate()+"','"+dto.getQuantity()+"','"+dto.getUnitOfMeasure()+"','"+dto.getItemId()+"','"+dto.getItemDescription()+"'," +
                "'"+dto.getUomCode()+"','"+dto.getShipToLocationId()+"','"+dto.getPrimaryQuantity()+"','"+dto.getReceiptSourceCode()+"'," +
                "'"+dto.getVendorId()+"','"+dto.getVendorSiteId()+"','"+dto.getToOrganizationId()+"','"+dto.getPoHeaderId()+"'," +
                "'"+dto.getPoLineId()+"','"+dto.getPoLineLocationId()+"','"+dto.getPoUnitPrice()+"','"+dto.getCurrencyCode()+"'," +
                "'"+dto.getSourceDocumentCode()+"','"+dto.getCurrencyConversionType()+"','"+dto.getCurrencyConversionRate()+"'," +
                "'"+dto.getCurrencyConversionDate()+"','"+dto.getPoDistributionId()+"','"+dto.getDestinationTypeCode()+"','"+dto.getLocationId()+"','"+dto.getDeliverToLocationId()+"'," +
                "'"+dto.getInspectionStatusCode()+"','"+dto.getHeaderInterfaceId()+"','"+dto.getVendorSiteCode()+"','"+dto.getProcessingStatusCode()+"'," +
                "'"+dto.getUseMtlLot()+"','"+dto.getUseMtlSerial()+"','"+dto.getTransactionStatusCode()+"','"+dto.getValidationFlag()+"','"+dto.getOrgId()+"','"+dto.getGroupId()+"','"+dto.getAutoTransactCode()+"')";

        SQLiteDatabase db = this.getConn(context);
        try {
            db.execSQL(sql);
            res = 1;
            db.close();
        }catch (Exception e){
            Log.d("Error Base",e.getMessage());
        }

        return res;

    }

    public int insertLote(Context context, MtlTransactionLotsIfaceDto dto){
        int res = 0;

        String sql = "INSERT INTO "+Utilidades.TABLA_MTL_TRANSACTIONS_LOTS_IFACE+"(mtl_transaction_lots_iface, last_update_date,"+
                "last_update_by, creation_date, created_by, po_header_id, po_line_id, inventory_item_id, last_update_login,"+
                "lot_number, transaction_quantity, primary_quantity, serial_transaction_temp_id, product_code, product_transaction_id,"+
                "supplier_lot_number, lot_expiration_date, attribute_category, attribute1, attribute2, attribute3) VALUES ('"+dto.getMtlTransactionLotsIface()+"'," +
                "'"+dto.getLastUpdateDate()+"','"+dto.getLastUpdateBy()+"','"+dto.getCreationDate()+"','"+dto.getCreatedBy()+"'," +
                "'"+dto.getPoHeaderId()+"','"+dto.getPoLineId()+"','"+dto.getInventoryItemId()+"','"+dto.getLastUpdateLogin()+"', " +
                "'"+dto.getLotNumber()+"','"+dto.getTransactionQuantity()+"','"+dto.getPrimaryQuantity()+"','"+dto.getSerialTransactionTempId()+"', " +
                "'"+dto.getProductCode()+"','"+dto.getProductTransactionId()+"','"+dto.getSupplierLotNumber()+"','"+dto.getLotExpirationDate()+"', " +
                "'"+dto.getAttributeCategory()+"','"+dto.getAttribute1()+"','"+dto.getAttribute2()+"','"+dto.getAttribute3()+"')";

        SQLiteDatabase db = this.getConn(context);
        try {
            db.execSQL(sql);
            res = 1;
            db.close();
        }catch (Exception e){
            Log.d("Error Base",e.getMessage());
        }

        return res;

    }





}
