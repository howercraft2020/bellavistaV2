package cl.clsoft.bave.dao.rowmapper;

import android.database.Cursor;

import java.sql.SQLException;

import cl.clsoft.bave.dao.catalogo.LocalizadorCatalogo;
import cl.clsoft.bave.dao.catalogo.MtlCycleCountEntriesCatalogo;
import cl.clsoft.bave.model.Localizador;

public class LocalizadorRowMapper implements RowMapper<Localizador> {
    @Override
    public Localizador mapRow(Cursor cursor, int position) throws SQLException {
        Localizador entity = new Localizador();
        entity.setIdLocalizador(cursor.getLong(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_ID)));
        entity.setOrganizationId(cursor.getLong(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_ORG_ID)));
        entity.setCodSubinventario(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SUBINV)));
        entity.setCodLocalizador(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_LOC)));
        entity.setCodSeg1(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG1)));
        entity.setCodSeg2(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG2)));
        entity.setCodSeg3(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG3)));
        entity.setCodSeg4(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG4)));
        entity.setCodSeg5(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG5)));
        entity.setCodSeg6(cursor.getString(cursor.getColumnIndex(LocalizadorCatalogo.COLUMN_COD_SEG6)));
        return entity;
    }
}
