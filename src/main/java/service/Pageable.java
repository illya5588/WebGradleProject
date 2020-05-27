package service;

public class Pageable {
    int pages;
    String criteria;
    String order;
    String success;

    public Pageable() {
    }

    public Pageable(int pages, String criteria, String order, String success) {
        this.pages = pages;
        this.criteria = criteria;
        this.order = order;
        this.success = success;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
