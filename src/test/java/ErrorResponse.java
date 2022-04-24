public class ErrorResponse {

    private String status;
    private String error;

    public ErrorResponse() {}

    public ErrorResponse(String status, String original) {
        this.status = status;
        this.error = original;
    }

    public String getStatus() {
        return status;
    }

    public ErrorResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ErrorResponse setError(String error) {
        this.error = error;
        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(status, error);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponse response = (ErrorResponse) o;

        if (!status.equals(response.status)) return false;
        return error.equals(response.error);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + error.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", original='" + error + '\'' +
                '}';
    }
}
