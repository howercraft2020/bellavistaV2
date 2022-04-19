package cl.clsoft.bave.apis;


public class ApiUtils {


    public static final String BASE_URL ="https://10.13.30.31:8443/InventarioREST-0.0.1-SNAPSHOT/";

    //public static final String BASE_URL = "https://6034-200-68-46-18.ngrok.io/";

    public static IRestPoheadersAll getIRestPoheadersAll() {
        return RetrofitClient.getClient(BASE_URL).create(IRestPoheadersAll.class);
    }

    public static IRestMtlPhysicalInventories getIRestMtlPhysicalInventories(){
        return RetrofitClient.getClient(BASE_URL).create(IRestMtlPhysicalInventories.class);
    }

    public static IRestPoheadersAll getIRestPoheadersAllResponse() {
        return RetrofitClient.getClientResponse(BASE_URL).create(IRestPoheadersAll.class);
    }
    public static IRestMtlPhysicalInventoryTags getIRestMtlPhysicalInventoryTagsResponse(){
        return RetrofitClient.getClientResponse(BASE_URL).create(IRestMtlPhysicalInventoryTags.class);
    };

    public static IRestPoheadersAll getIRestPoheadersAllRx() {
        return RetrofitClientRx.getClient(BASE_URL).create(IRestPoheadersAll.class);
    }
    //
    public static IRestHomologacion getIRestHomologacion(){
        return RetrofitClient.getClient(BASE_URL).create(IRestHomologacion.class);

    }

    public static IRestRcvTransactionsInterface getIRestTransactionInterface(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvTransactionsInterface.class);
    }
    public static IRestDatosRecepcion getIRestDatosRecepcion(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestDatosRecepcion.class);
    }

    public static IRestDatosRecepcion getIRestDatosRecepcionV2(){

        return RetrofitClient.getClient(BASE_URL).create(IRestDatosRecepcion.class);
    }

    public static IRestMtlSystemItems getIRestMtlSystemItems(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlSystemItems.class);

    }
    public static IRestOrganizacionPrincipal getIRestOrganizacionPrincipal(){

        return RetrofitClient.getClient(BASE_URL).create(IRestOrganizacionPrincipal.class);

    }
    public static IRestOrganizacionPrincipal getIRestOrganizacionPrincipalRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestOrganizacionPrincipal.class);

    }
    public static IRestPoDistributionsAll getIRestPoDistributionsAlll(){

        return RetrofitClient.getClient(BASE_URL).create(IRestPoDistributionsAll.class);

    }
    public static IRestPoDistributionsAll getIRestPoDistributionsAllRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestPoDistributionsAll.class);

    }
    public static IRestPoLineLocationsAll getIRestPoLineLocationsAll(){

        return RetrofitClient.getClient(BASE_URL).create(IRestPoLineLocationsAll.class);

    }
    public static IRestPoLineLocationsAll getIRestPoLineLocationsAllRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestPoLineLocationsAll.class);

    }

    public static IRestPoLinesAll getIRestPoLinesAll(){

        return RetrofitClient.getClient(BASE_URL).create(IRestPoLinesAll.class);

    }
    public static IRestPoLinesAll getIRestPoLinesAllRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestPoLinesAll.class);

    }
    public static IRestRcvHeadersInterface getIRestRcvHeadersInterface(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvHeadersInterface.class);

    }


    public static IRestRcvStatus getIRestRcvStatus(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvStatus.class);
    }


    public static IRestRcvStatus getIRestRcvStatusRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvStatus.class);
    }


    public static IRestRcvShipmentHeaders getIRestRcvShipmentHeaders(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvShipmentHeaders.class);
    }
    public static IRestRcvShipmentHeaders getIRestRcvShipmentHeadersRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvShipmentHeaders.class);
    }

    public static IRestRcvTransactions getIRestRcvTransactions(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvTransactions.class);
    }

    public static IRestRcvHeadersInterface getIRestRcvHeadersInterfaceRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvHeadersInterface.class);

    }
    public static IRestRcvTransactions getIRestRcvTransactionsRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvTransactions.class);
    }

    public static IRestRcvTransactionsInterface getIRestTransactionInterfaceRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvTransactionsInterface.class);
    }

    public static IRestMtlSystemItems getIRestMtlSystemItemsRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlSystemItems.class);

    }

    public static IRestLocalizador getIRestLocalizador(){

        return RetrofitClient.getClient(BASE_URL).create(IRestLocalizador.class);

    }

    public static IRestLocalizador getIRestLocalizadorRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestLocalizador.class);

    }

    public static IRestSubinventario getIRestSubinventario(){
        return RetrofitClient.getClient(BASE_URL).create(IRestSubinventario.class);

    }

    public static IRestSubinventario getIRestSubinventarioRx(){
        return RetrofitClientRx.getClient(BASE_URL).create(IRestSubinventario.class);

    }

    public static  IRestMtlMaterialTransactions getIRestMtlMaterialTransactions(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlMaterialTransactions.class);
    }

    public static  IRestMtlMaterialTransactions getIRestMtlMaterialTransactionsRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlMaterialTransactions.class);
    }
    public static IRestMtlTransactionLotNumbers getIRestMtlTransactionLotNumbers(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlTransactionLotNumbers.class);

    }
    public static IRestMtlTransactionLotNumbers getIRestMtlTransactionLotNumbersRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlTransactionLotNumbers.class);

    }
    public static  IRestMtlSerialNumbers getIRestMtlSerialNumbers(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlSerialNumbers.class);

    }

    public static  IRestMtlSerialNumbers getIRestMtlSerialNumbersRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlSerialNumbers.class);

    }

    public static IRestXXPDA_MTL_SERIAL_NUM_IFACE getIRestXXPDA_MTL_SERIAL_NUM_IFACE(){


        return RetrofitClient.getClient(BASE_URL).create(IRestXXPDA_MTL_SERIAL_NUM_IFACE.class);
    }


    public static IRestXXPDA_MTL_SERIAL_NUM_IFACE getIRestXXPDA_MTL_SERIAL_NUM_IFACERx(){


        return RetrofitClientRx.getClient(BASE_URL).create(IRestXXPDA_MTL_SERIAL_NUM_IFACE.class);
    }




    public static IRestXXPDA_MTL_TRANS_LOTS_IFACE getIRestXXPDA_MTL_TRANS_LOTS_IFACE(){

        return RetrofitClient.getClient(BASE_URL).create(IRestXXPDA_MTL_TRANS_LOTS_IFACE.class);

    }


    public static IRestXXPDA_MTL_TRANS_LOTS_IFACE getIRestXXPDA_MTL_TRANS_LOTS_IFACERx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestXXPDA_MTL_TRANS_LOTS_IFACE.class);

    }

    public static IRestXXPDA_RCV_HEADERS_INTERFACE getIRestXXPDA_RCV_HEADERS_INTERFACE(){

        return RetrofitClient.getClient(BASE_URL).create(IRestXXPDA_RCV_HEADERS_INTERFACE.class);

    }

    public static IRestXXPDA_RCV_HEADERS_INTERFACE getIRestXXPDA_RCV_HEADERS_INTERFACERx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestXXPDA_RCV_HEADERS_INTERFACE.class);

    }

    public static IRestXXPDA_RCV_TRANS_INTERFACE getIRestXXPDA_RCV_TRANS_INTERFACE(){

        return RetrofitClient.getClient(BASE_URL).create(IRestXXPDA_RCV_TRANS_INTERFACE.class);

    }

    public static IRestXXPDA_RCV_TRANS_INTERFACE getIRestXXPDA_RCV_TRANS_INTERFACERx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestXXPDA_RCV_TRANS_INTERFACE.class);

    }

    public static IREstMtlTransactionsLotsIface getIREstMtlTransactionsLotsIface(){

        return RetrofitClient.getClient(BASE_URL).create(IREstMtlTransactionsLotsIface.class);
    }

    public static IRestMtlSerialNumbersInterface getIRestMtlSerialNumbersInterface(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlSerialNumbersInterface.class);

    }
    public static IRestConsulta getIRestConsulta(){

        return RetrofitClient.getClient(BASE_URL).create(IRestConsulta.class);

    }

    public static IRestRcvHeadersInterfaceEntrega getIRestRcvHeadersInterfaceEntrega(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvHeadersInterfaceEntrega.class);

    }

    public static  IRestRcvTransactionsInterfaceEntrega getIRestRcvTransactionsInterfaceEntrega(){

        return RetrofitClient.getClient(BASE_URL).create(IRestRcvTransactionsInterfaceEntrega.class);
    }

    public static IRestRcvHeadersInterfaceEntrega getIRestRcvHeadersInterfaceEntregaRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvHeadersInterfaceEntrega.class);

    }

    public static  IRestRcvTransactionsInterfaceEntrega getIRestRcvTransactionsInterfaceEntregaRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestRcvTransactionsInterfaceEntrega.class);
    }

    public static IRestMtlCycleCountHeaders getIRestMtlCycleCountHeaders(){

        return RetrofitClient.getClient(BASE_URL).create(IRestMtlCycleCountHeaders.class);

    }
    public static IRestMtlCycleCountHeaders getIRestMtlCycleCountHeadersRx(){

        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlCycleCountHeaders.class);

    }

    public static IRestMtlCycleCountEntries getIRestMtlCycleCountEntries(){


        return RetrofitClient.getClient(BASE_URL).create(IRestMtlCycleCountEntries.class);
    }

    public static IRestMtlCycleCountEntries getIRestMtlCycleCountEntriesRx(){


        return RetrofitClientRx.getClient(BASE_URL).create(IRestMtlCycleCountEntries.class);
    }


}