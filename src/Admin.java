
import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Permissions> permissions = new ArrayList<>();
    private boolean isSuperAdmin;

    public Admin() {
    }

    public Admin(Profile profile, String username, String password, ArrayList<Permissions> permissions, boolean isSuperAdmin) {
        super(profile, username, password);
        this.permissions = permissions;
        this.isSuperAdmin = isSuperAdmin;
    }

    public enum Permissions {
        READ,
        WRITE,
        DELETE
    }

    public ArrayList<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permissions> permissions) {
        this.permissions = permissions;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
}
