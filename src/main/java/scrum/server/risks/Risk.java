/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package scrum.server.risks;

import ilarkesto.core.base.Utl;

import java.util.List;

import scrum.client.risks.RiskComputer;
import scrum.server.admin.User;
import scrum.server.common.Numbered;

public class Risk extends GRisk implements Numbered, Comparable<Risk> {

	// --- copies ---

	@Override
	public List<Integer> getProbabilityOptions() {
		return scrum.client.risks.Risk.PROBABILITIES;
	}

	@Override
	public List<Integer> getImpactOptions() {
		return scrum.client.risks.Risk.IMPACTS;
	}

	// --- ---

	public int getPriority() {
		return RiskComputer.getPriority(getImpact(), getProbability());
	}

	public String getPriorityLabel() {
		return RiskComputer.getPriorityLabel(getImpact(), getProbability());
	}

	public String getProbabilityLabel() {
		return RiskComputer.getProbabilityLabel(getProbability());
	}

	public String getImpactLabel() {
		return RiskComputer.getImpactLabel(getImpact());
	}

	@Override
	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateRiskNumber());
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return scrum.client.risks.Risk.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void onEnsureIntegrity() {
		super.onEnsureIntegrity();
		updateNumber();

	}

	@Override
	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public int compareTo(Risk other) {
		return Utl.compare(other.getPriority(), getPriority());
	}

	@Override
	public String asString() {
		return getReferenceAndLabel();
	}

}
