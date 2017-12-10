package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise", foreignKeys = @ForeignKey(entity =
															TrainingPlan.class,
															onDelete = CASCADE,
															parentColumns = "id",
															childColumns = "plan_id"))
public class Exercise implements Parcelable
{
	public Exercise()
	{
	}

	public Exercise(int id, String name, int planId)
	{
		Id = id;
		Name = name;
		this.planId = planId;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	@ColumnInfo(name = "plan_id")
	private int planId;

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
		this.Name = name;
	}


	public int getPlanId()
	{
		return planId;
	}

	public void setPlanId(int planId)
	{
		this.planId = planId;
	}

	protected Exercise(Parcel in)
	{
		Id = in.readInt();
		Name = in.readString();
		planId = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(Id);
		dest.writeString(Name);
		dest.writeInt(planId);
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	public static final Creator<Exercise> CREATOR = new Creator<Exercise>()
	{
		@Override
		public Exercise createFromParcel(Parcel in)
		{
			return new Exercise(in);
		}

		@Override
		public Exercise[] newArray(int size)
		{
			return new Exercise[size];
		}
	};
}
