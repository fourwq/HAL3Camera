package com.example.qwei.common;

import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SizeF;

/**
 * Created by camera on 2016/5/6.
 */
public class Util {
    public static String getStringFromStringArray(String[] array){
        StringBuilder builder = new StringBuilder();
        for (String item:array
             ) {
            builder.append(item+"\n");
        }
        return  builder.toString();
    }

    public static  String  getCameraIdListInfo(CameraManager manager){
        String[] list;
        try {
            list =  manager.getCameraIdList();
        } catch (CameraAccessException e) {
           return "CameraAccessException";
        }
        if(list !=null) {
            return getStringFromStringArray(list);
        }else{
            return "NULL";
        }
    }

    static String  getSupportedHardwareLevel(CameraCharacteristics characteristics){
        StringBuilder builder = new StringBuilder();
        builder.append(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL.getName()+ ": ");
        int level = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        switch(level){
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL:
                builder.append("INFO_SUPPORTED_HARDWARE_LEVEL_FULL");
                break;
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY:
                builder.append("INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY");
                break;
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED:
                builder.append("INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED");
                break;
        }
        return builder.toString();
    }

    static String getIntArrayString(int[] intArray){
        StringBuilder builder = new StringBuilder();
        for(int i: intArray){
            builder.append(i+" ");
        }
        return builder.toString();
    }

    static String getBooleanArrayString(boolean[] booleanArray){
        StringBuilder builder = new StringBuilder();
        for(boolean i: booleanArray){
            builder.append(i+" ");
        }
        return builder.toString();
    }

    static String getFloatArrayString(float[] floatArray){
        StringBuilder builder = new StringBuilder();
        for(float f: floatArray){
            builder.append(f+" ");
        }
        return builder.toString();
    }

    static String getRangeArrayString(Range[] rangeArray){
        StringBuilder builder = new StringBuilder();
       for(Range range: rangeArray){
          if(range.getLower() instanceof  Integer){
                builder.append(getRangeIntegerString(range));
               builder.append("; ");
          }
       }
        return builder.toString();
    }

    static String getRangeIntegerString(Range<Integer> range){
            Integer min = range.getLower();
            Integer max = range.getUpper();
            return "["+min.toString()+", "+max.toString()+"]";
    }

    static String getRangeLongString(Range<Long> range){
        Long min = range.getLower();
        Long max = range.getUpper();
        return "["+min.toString()+", "+max.toString()+"]";
    }

    static String getKeyString(Object obj){
        if(obj instanceof int[]){
                return getIntArrayString((int[]) obj);
        }else if(obj instanceof boolean[]){
                return getBooleanArrayString((boolean[]) obj);
        }else if(obj instanceof Range[]){
                return  getRangeArrayString((Range[]) obj);
        }else if(obj instanceof  Range){
                return  getRangeString((Range) obj);
        }else if(obj instanceof  Boolean){
             return getBooleanString((Boolean) obj);
        }else if(obj instanceof Rational){
            return getRationalString((Rational) obj);
        }else if(obj instanceof  Integer){
            return getIntegerString((Integer) obj);
        }else if(obj instanceof Size[]){
            return getSizeArrayString((Size[]) obj);
        }else if(obj instanceof  float[]){
            return getFloatArrayString((float[]) obj);
        }else if(obj instanceof Float){
            return getFloatString((Float) obj);
        }else if(obj instanceof  Byte){
            return getByteString((Byte) obj);
        }else if(obj instanceof  Size){
            return getSizeString((Size) obj);
        }else if(obj instanceof Rect){
            return getRectString((Rect) obj);
        }else if(obj instanceof SizeF){
            return  getSizeFString((SizeF) obj);
        }else if(obj instanceof Long){
            return  getLongString((Long) obj);
        }else if(obj instanceof ColorSpaceTransform){
            return  getColorSpaceTransformString((ColorSpaceTransform) obj);
        }else if(obj instanceof BlackLevelPattern){
            return getBlackLevelPatternString((BlackLevelPattern) obj);
        }else if(obj instanceof StreamConfigurationMap){
            return getStreamConfigureationMapString((StreamConfigurationMap) obj);
        }

        return "//todo";
    }


    private static String getStreamConfigureationMapString(StreamConfigurationMap map){
        return map.toString();
    }

    private static String getBlackLevelPatternString(BlackLevelPattern pattern){
        return pattern.toString();
    }

    private static String getColorSpaceTransformString(ColorSpaceTransform c){
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append(getRationalString(c.getElement(0,0))+"\t");
        builder.append(getRationalString(c.getElement(0,1))+"\t");
        builder.append(getRationalString(c.getElement(0,2))+"\t");
        builder.append("\n");
        builder.append(getRationalString(c.getElement(1,0))+"\t");
        builder.append(getRationalString(c.getElement(1,1))+"\t");
        builder.append(getRationalString(c.getElement(1,2))+"\t");
        builder.append("\n");
        builder.append(getRationalString(c.getElement(2,0))+"\t");
        builder.append(getRationalString(c.getElement(2,1))+"\t");
        builder.append(getRationalString(c.getElement(2,2))+"\t");
        builder.append("\n");
        return builder.toString();
    }

    private static String getSizeFString(SizeF sizef){
        return sizef.getWidth()+" X "+sizef.getHeight()+";";
    }
    private static String getRectString(Rect rect){
        StringBuilder builder = new StringBuilder();
        builder.append("["+rect.left+","+rect.top+",");
        builder.append(rect.right+","+rect.bottom+"]");
        return builder.toString();
    }

    private static String getByteString(Byte b){
        return b.toString();
    }

    private static String getSizeString(Size z){
        return z.toString();
    }
    private static String getLongString(Long l){
        return l.toString();
    }

    private static String getSizeArrayString(Size[] sizes){
        StringBuilder builder = new StringBuilder();
            for(Size size: sizes){
                builder.append(size.getWidth()+"X"+size.getHeight()+";");
            }
        return builder.toString();
    }

    private static String getRationalString(Rational r){
        return r.toString();
    }

    private static String getIntegerString(Integer i){
        return i.toString();
    }

    private static String getFloatString(Float f){
        return f.toString();
    }

    private static String getBooleanString(Boolean b){
        return b.toString();
    }

    private static String getRangeString(Range range){
        Object min = range.getLower();
        if(min instanceof Integer){
           return  getRangeIntegerString(range);
        }else if(min instanceof Long){
            return getRangeLongString(range);
        }
        return "//todo";
    }
}
