/* @Pedro
   -----
   BD  = BigDecimal
   INT = Integer
   C   = Comparator
   DE  = Delimitador
   DI  = Distinto
   L   = Lista
   F   = Function
   P   = Predicate
   OE  = orElse
   PR  = String Prefixo
   SU  = String Sufixo
   VMX = Valor Máximo
   VMI = Valor Mínimo
*/
package com.sysagro.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;

/**
 *
 * @author Pedro
 * @param <T>
 * @param <R>
 */
public final class LambdaUtil<T, R> implements Serializable {

    // Geral
    public List<T> processarLP(Collection<T> lista, Predicate<T> predicate) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().filter(predicate).collect(Collectors.toList());
    }
    
    public List<T> processarLPDI(Collection<T> lista, Predicate<T> predicate) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().filter(predicate).distinct().collect(Collectors.toList());
    }
    
    public List<R> processarLF(Collection<T> lista, Function<T, R> function) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().map(function).collect(Collectors.toList());
    }
    
    public List<R> processarLFDI(Collection<T> lista, Function<T, R> function) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().map(function).distinct().collect(Collectors.toList());
    }
    
    public List<R> processarLPF(Collection<T> lista, Predicate<T> predicate, Function<T, R> function) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().filter(predicate).map(function).collect(Collectors.toList());
    }
    
    public List<R> processarLFP(Collection<T> lista, Function<T, R> function, Predicate<R> predicate) {
        return CollectionUtils.isEmpty(lista)
            ? Lists.newArrayList()
            : lista.stream().map(function).filter(predicate).collect(Collectors.toList());
    }
    
    public R processarLPOE(Collection<T> lista, Predicate predicate, R orElse) {
		return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().filter(predicate).findFirst().orElse(orElse));
	}
    
    public R processarLFOE(Collection<T> lista, Function<T, R> function, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).findFirst().orElse(orElse));
	}
	
	public R processarLPFOE(Collection<T> lista, Predicate predicate, Function<T, R> function, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().filter(predicate).map(function).findFirst().orElse(orElse));
	}

	public R processarLFPOE(Collection<T> lista, Function<T, R> function, Predicate predicate, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).filter(predicate).findFirst().orElse(orElse));
	}

    public R processarLFPCVMIOE(Collection<T> lista, Function<T, R> function, Predicate<R> predicate, Comparator<R> comparator, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).filter(predicate).distinct().collect(Collectors.minBy(comparator)).orElse(orElse));
	}

	public R processarLFCVMIOE(Collection<T> lista, Function<T, R> function, Comparator<R> comparator, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).distinct().collect(Collectors.minBy(comparator)).orElse(orElse));
	}

	public R processarLFPCVMXOE(Collection<T> lista, Function<T, R> function, Predicate<R> predicate, Comparator<R> comparator, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).filter(predicate).distinct().collect(Collectors.maxBy(comparator)).orElse(orElse));
	}

	public R processarLFCVMXOE(Collection<T> lista, Function<T, R> function, Comparator<R> comparator, R orElse) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : (R) (lista.stream().map(function).distinct().collect(Collectors.maxBy(comparator)).orElse(orElse));
	}

	public String unificarLFDE(Collection<T> lista, Function<T, String> function, String delimitador) {
		return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().map(function).collect(Collectors.joining(delimitador));
	}

	public String unificarLPFDE(Collection<T> lista, Predicate<T> predicate, Function<T, String> function, String delimitador) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().filter(predicate).map(function).collect(Collectors.joining(delimitador));
	}

	public String unificarLFPDE(Collection<T> lista, Function<T, String> function, Predicate<String> predicate, String delimitador) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().map(function).filter(predicate).collect(Collectors.joining(delimitador));
	}

	public String unificarLFDIDE(Collection<T> lista, Function<T, String> function, String delimitador) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().map(function).distinct().collect(Collectors.joining(delimitador));
	}
	
	public String unificarLPFDIDE(Collection<T> lista, Predicate<T> predicate, Function<T, String> function, String delimitador) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().filter(predicate).map(function).distinct().collect(Collectors.joining(delimitador));
	}
    
    public String unificarLFDEPRSU(Collection<T> lista, Function<T, String> function, String delimitador, String prefixo, String sufixo) {
        return CollectionUtils.isEmpty(lista)
            ? null
            : lista.stream().map(function).collect(Collectors.joining(delimitador, prefixo, sufixo));
	}

    public Long contarLP(Collection<T> lista, Predicate<T> predicate) {
        return CollectionUtils.isEmpty(lista)
            ? 0
            : lista.stream().filter(predicate).count();
	}

	public Long contarLFP(Collection<T> lista, Function<T, R> function, Predicate<R> predicate) {
        return CollectionUtils.isEmpty(lista)
            ? 0
            : lista.stream().map(function).filter(predicate).count();
	}

	public BigDecimal somarLFBD(Collection<T> lista, Function<T, BigDecimal> function) {
		return CollectionUtils.isEmpty(lista)
            ? BigDecimal.ZERO
            : lista.stream().map(function).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
    
    public Integer somarLPFINT(Collection<T> lista, Predicate<T> predicate, Function<T, Integer> function) {
		return CollectionUtils.isEmpty(lista)
            ? 0
            : lista.stream().filter(predicate).map(function).reduce(0, (v1, v2) -> NumeroUtil.validarInteger(v1) + NumeroUtil.validarInteger(v2));
	}

	public BigDecimal somarLPFBD(Collection<T> lista, Predicate<T> predicate, Function<T, BigDecimal> function) {
		return CollectionUtils.isEmpty(lista)
            ? BigDecimal.ZERO
            : lista.stream().filter(predicate).map(function).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal somarLFPBD(Collection<T> lista, Function<T, BigDecimal> function, Predicate<BigDecimal> predicate) {
		return CollectionUtils.isEmpty(lista)
            ? BigDecimal.ZERO
            : lista.stream().map(function).filter(predicate).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}