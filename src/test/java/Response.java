public class Response {

    private String status;
    private String original;
    private String converted;
    private String from;
    private String to;

    public Response() {}

    public Response(String status, String original, String converted, String from, String to) {
        this.status = status;
        this.original = original;
        this.converted = converted;
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOriginal() {
        return original;
    }

    public Response setOriginal(String original) {
        this.original = original;
        return this;
    }

    public String getConverted() {
        return converted;
    }

    public Response setConverted(String converted) {
        this.converted = converted;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Response setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Response setTo(String to) {
        this.to = to;
        return this;
    }

    public Response build() {
        return new Response(status, original, converted, from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (!status.equals(response.status)) return false;
        if (!original.equals(response.original)) return false;
        if (!converted.equals(response.converted)) return false;
        if (!from.equals(response.from)) return false;
        return to.equals(response.to);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + original.hashCode();
        result = 31 * result + converted.hashCode();
        result = 31 * result + from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", original='" + original + '\'' +
                ", converted='" + converted + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
