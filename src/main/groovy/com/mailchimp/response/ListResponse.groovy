package com.mailchimp.response

class ListResponse<T> implements Iterable<T> {
    private List<T> list = new ArrayList<>()

    private Integer totalCount

    public Iterator<T> iterator() {
        return list.iterator()
    }
}