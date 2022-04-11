package cl.clsoft.bave.model;

public class RcvStatus {
    private Long po_header_id;
    private Long receip_num;
    private int process_flag;

    public RcvStatus() {
    }

    public Long getPo_header_id() {
        return po_header_id;
    }

    public void setPo_header_id(Long po_header_id) {
        this.po_header_id = po_header_id;
    }

    public Long getReceip_num() {
        return receip_num;
    }

    public void setReceip_num(Long receip_num) {
        this.receip_num = receip_num;
    }

    public int getProcess_flag() {
        return process_flag;
    }

    public void setProcess_flag(int process_flag) {
        this.process_flag = process_flag;
    }
}
