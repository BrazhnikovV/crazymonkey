package funkymonkey.com.spritesList;

import funkymonkey.com.base.Sprite;

import java.util.ArrayList;
import java.util.List;

public class SymbolsList <T extends Sprite> {

    /**
     *  @access protected
     *  @var List<T> activeObjects - лист активных спрайтов
     */
    private List<T> activeObjects = new ArrayList<T>();

    /**
     * addSumbol -
     * @param object
     */
    public void addSymbol ( T object ) {
        this.activeObjects.add( object );
    }

    /**
     * getSumbol -
     * @param index -
     * @return
     */
    public T getSymbol ( int index ) {
        return this.activeObjects.get( index );
    }
}
