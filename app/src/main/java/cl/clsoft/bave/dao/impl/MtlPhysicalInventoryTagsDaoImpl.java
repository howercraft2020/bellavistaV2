package cl.clsoft.bave.dao.impl;

import android.content.ContentValues;

import cl.clsoft.bave.dao.IMtlPhysicalInventoryTagsDao;
import cl.clsoft.bave.dao.catalogo.MtlPhysicalInventoryTagsCatalogo;
import cl.clsoft.bave.exception.DaoException;
import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class MtlPhysicalInventoryTagsDaoImpl extends GenericDao<MtlPhysicalInventoryTags> implements IMtlPhysicalInventoryTagsDao {

    @Override
    public void insert(MtlPhysicalInventoryTags mtlPhysicalInventoryTags) throws DaoException {
        ContentValues values = new ContentValues();
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_TAG_ID, mtlPhysicalInventoryTags.getTagId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PHYSICAL_INVENTORY_ID, mtlPhysicalInventoryTags.getPhysicalInventoryId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_ORGANIZATION_ID, mtlPhysicalInventoryTags.getOrganizationId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_INVENTORY_ITEM_ID, mtlPhysicalInventoryTags.getInventoryItemId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SUBINVENTORY, mtlPhysicalInventoryTags.getSubinventory());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOCATOR_ID, mtlPhysicalInventoryTags.getLocatorId());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_NUMBER, mtlPhysicalInventoryTags.getLotNumber());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_LOT_EXPIRATION_DATE, mtlPhysicalInventoryTags.getLotExpirationDate());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SERIAL_NUM, mtlPhysicalInventoryTags.getSerialNum());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_SEGMENT1, mtlPhysicalInventoryTags.getSegment1());
        values.put(MtlPhysicalInventoryTagsCatalogo.COLUMN_PRIMARY_UOM_CODE, mtlPhysicalInventoryTags.getPrimaryUomCode());
        super.insert(MtlPhysicalInventoryTagsCatalogo.TABLE, values);
    }

    @Override
    public void deleteByPhysicalInventory(Long physicalInventoryId) throws DaoException {
        super.delete(MtlPhysicalInventoryTagsCatalogo.TABLE, MtlPhysicalInventoryTagsCatalogo.DELETE_BY_PHYSICAL_INVENTORY_ID, physicalInventoryId);
    }

}
