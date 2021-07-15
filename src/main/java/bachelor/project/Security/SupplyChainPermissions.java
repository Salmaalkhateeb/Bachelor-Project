package bachelor.project.Security;

public enum SupplyChainPermissions {
    RETAILER_READ("retailer:read"),
    RETAILER_WRITE("retailer:write"),
    TRANSACTION_READ("transaction:read"),
    PASSWORD_READ("password:read"),
    PASSWORD_WRITE("password:write"),
    LOGIN_READ("login:read"),
    TRANSACTION_WRITE("transaction:write"),
    LOGIN_WRITE("login:write");


    private final String permission;

    SupplyChainPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
