package zy.news.web.zsys.bean;

import maoko.common.StringUtil;

/**
 * 权限类型
 *
 * @author fanpei
 */
public enum PermissionType {
    全部(0),

    读取(1),

    添加(2),

    修改(3),

    删除(4);

    private int value;

    private PermissionType(int value) {
        this.value = value;
    }

    public static PermissionType getByValue(int value) {
        PermissionType type = null;
        switch (value) {
            case 0:
                type = PermissionType.全部;
                break;
            case 1:
                type = PermissionType.读取;
                break;
            case 2:
                type = PermissionType.添加;
                break;
            case 3:
                type = PermissionType.修改;
                break;
            case 4:
                type = PermissionType.删除;
                break;
            default:
                type = PermissionType.全部;
        }
        return type;
    }

    public static String getTypeStr(String types) {
        StringBuilder typeBuilder = null;
        if (StringUtil.isStrNullOrWhiteSpace(types))
            return PermissionType.全部.toString();
        else {
            if (types.contains("0"))
                return PermissionType.全部.toString();
            String[] typeArray = types.split(",");
            typeBuilder = new StringBuilder();
            for (int i = 0; i < typeArray.length; i++) {
                String typeStr = typeArray[i];
                PermissionType pt = getByValue(Integer.parseInt(typeStr));
                typeBuilder.append(pt);
                if (i != typeArray.length - 1)
                    typeBuilder.append(",");
            }
        }
        return typeBuilder.toString();
    }

    public static PermissionType[] getTypes(String types) {
        PermissionType[] permissionTypes = null;
        if (StringUtil.isStrNullOrWhiteSpace(types))
            return new PermissionType[]{PermissionType.全部};
        else {
            if (types.contains("0"))
                return new PermissionType[]{PermissionType.全部};
            String[] typeArray = types.split(",");
            permissionTypes = new PermissionType[typeArray.length];
            for (int i = 0; i < typeArray.length; i++) {
                String typeStr = typeArray[i];
                PermissionType pt = getByValue(Integer.parseInt(typeStr));
                permissionTypes[i] = pt;
            }
        }
        return permissionTypes;
    }
}
