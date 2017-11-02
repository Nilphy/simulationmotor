package com.wesimulated.simulationmotor.des.activityScanningAproach;

import java.util.Date;
import java.util.LinkedList;

import com.wesimulated.simulation.runparameters.EndCondition;
import com.wesimulated.simulationmotor.des.COperation;
import com.wesimulated.simulationmotor.des.OperationBasedExecutor;

/**
 * En un simulador de tipo "Activity Scanning" todas las operaciones son
 * operaciones de tipo C. Con una cabecera para evaluar si se cumplieron los
 * requerimientos para ejecutar la operación...
 * 
 * Es menos eficiente que el de tipo "three phase" porque no se aprovecha la
 * ventaja de que se sabe que hay tareas que ocurren en un momento preciso y no
 * hay que estar verificando en todos los momentos en que se ejecutan tasks.
 *
 * @author Carolina
 *
 */
public class ActivityScanningExecutor extends OperationBasedExecutor {

	public ActivityScanningExecutor(EndCondition endCondition) {
		super(endCondition);
		this.setCOperations(new LinkedList<>());
	}

	@Override
	protected void doCicle() {
		Date nextDate = this.findNextDateOfPosibleStateChange();
		this.getClock().advanceUntil(nextDate);
		this.execCPhase();
	}

	private Date findNextDateOfPosibleStateChange() {
		Date actualDate = this.getClock().getCurrentDate();
		Date nextDate = null;
		for (COperation cOperation : this.getCOperations()) {
			if (cOperation.dateOfOccurrenceIsBetween(actualDate, nextDate)) {
				nextDate = cOperation.getDateOfOccurrence(actualDate);
			}
		}
		return nextDate;
	}
}
