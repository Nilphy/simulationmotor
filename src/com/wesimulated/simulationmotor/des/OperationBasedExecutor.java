package com.wesimulated.simulationmotor.des;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

import org.testng.collections.CollectionUtils;

import com.wesimulated.simulation.BaseExecutor;
import com.wesimulated.simulation.runparameters.EndCondition;

public abstract class OperationBasedExecutor extends BaseExecutor {
	private PriorityBlockingQueue<BOperationCore> bOperations;
	private List<COperationCore> cOperations;

	public OperationBasedExecutor(EndCondition endCondition) {
		super(endCondition);
		this.setBOperations(new PriorityBlockingQueue<>());
		this.setCOperations(new LinkedList<>());
	}

	public void addBOperation(BOperation bOperation) {
		this.bOperations.add(new BOperationCore(bOperation));
	}

	@SuppressWarnings("unchecked")
	public void addAllBOperations(Collection<BOperation> bOperations) {
		this.bOperations.addAll((Collection<BOperationCore>) bOperations.stream().map(bOperation -> {
			return new BOperationCore(bOperation);
		}));
	}

	public void addCOperation(COperation cOperation) {
		this.cOperations.add(new COperationCore(cOperation));
	}

	@SuppressWarnings("unchecked")
	public void addAllCOperations(Collection<COperation> cOperations) {
		this.cOperations.addAll((Collection<COperationCore>) cOperations.stream().map(cOperation -> {
			return new COperationCore(cOperation);
		}));
	}

	protected void execAPhase() throws InterruptedException {
		BOperationCore bOperation = bOperations.take();
		if (bOperation != null) {
			this.getClock().advanceUntil(bOperation.getStartTime());
		}
	}

	protected void execBPhase() {
		Collection<BOperationCore> operationsToRemove = new ArrayList<>();
		for (BOperationCore operation : this.bOperations) {
			if (this.getClock().hasThisDate(operation.getStartTime())) {
				operation.doAction();
				operationsToRemove.add(operation);
			}
		}
		if (CollectionUtils.hasElements(operationsToRemove)) {
			for (BOperationCore operation : operationsToRemove) {
				this.bOperations.remove(operation);
			}
		}
	}

	protected void execCPhase() {
		Collection<COperationCore> operationsToRemove = new ArrayList<>();
		Boolean somethingHasChanged = false;
		orderCOperationsBeforeTestThemForExcecution();
		for (COperationCore cOperation : cOperations) {
			if (cOperation.testIfRequirementsAreMet()) {
				cOperation.doAction();
				operationsToRemove.add(cOperation);
			}
		}
		for (COperationCore cOperation : operationsToRemove) {
			cOperations.remove(cOperation);
			somethingHasChanged = true;
		}
		if (somethingHasChanged) {
			// Retry again in case other operation can be made
			this.execCPhase();
		}
	}

	protected void setCOperations(List<COperationCore> emptyCollectionOfCOperations) {
		this.cOperations = emptyCollectionOfCOperations;
	}

	protected List<COperation> getCOperations() {
		return this.cOperations.stream().map((COperationCore cOperation) -> {
			return cOperation.getInnerOperation();
		}).collect(Collectors.toList());
	}

	protected void setBOperations(PriorityBlockingQueue<BOperationCore> emptyCollectionOfBOperations) {
		this.bOperations = emptyCollectionOfBOperations;
	}

	private void orderCOperationsBeforeTestThemForExcecution() {
		this.cOperations.sort(Comparator.comparing(COperationCore::getNegativePriority));
	}
}
