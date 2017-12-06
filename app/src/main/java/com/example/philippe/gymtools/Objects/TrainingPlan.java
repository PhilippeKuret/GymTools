package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "training_plan", indices = {@Index(value = {"name"}, unique = true)})
public class TrainingPlan implements Parcelable
{
	public TrainingPlan()
	{
	}

	public TrainingPlan(int id, String name, boolean isDisplayedPlan)
	{
		Id = id;
		Name = name;
		IsDisplayedPlan = isDisplayedPlan;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	@ColumnInfo(name = "is_displayed")
	private boolean IsDisplayedPlan;

	protected TrainingPlan(Parcel in)
	{
		Id = in.readInt();
		Name = in.readString();
		IsDisplayedPlan = in.readByte() != 0;
	}

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public boolean getIsDisplayedPlan()
	{
		return IsDisplayedPlan;
	}

	public void setIsDisplayedPlan(boolean displayedPlan)
	{
		IsDisplayedPlan = displayedPlan;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(Id);
		dest.writeString(Name);
		dest.writeByte((byte) (IsDisplayedPlan ? 1 : 0));
	}

	public static final Creator<TrainingPlan> CREATOR = new Creator<TrainingPlan>()
	{
		@Override
		public TrainingPlan createFromParcel(Parcel in)
		{
			return new TrainingPlan(in);
		}

		@Override
		public TrainingPlan[] newArray(int size)
		{
			return new TrainingPlan[size];
		}
	};
}
