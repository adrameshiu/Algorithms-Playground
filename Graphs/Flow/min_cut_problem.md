# Min Cut Problem

## Input graph

- input -> edge(`source`) weighted digraph(+ve weights)
- with source and sink

## What is a cut?

- `st-cut` -> partiton of vertices into 2 `disjoint sets` -> s and t are in two different sets

## What is the capacity of the cut?

- `sum` of `capacity of the OUTGOING edges` from A(set containing `s`) to B(set containing `t`)

## What is the min cut problem now?

- find the cut with `min capacity`-> min outgoing sum of weights from the set

# Max Flow Problem

## What is a flow?

- along with the capacities(which are positive), we need to add one more weight called the `flow` 
- `value` of the flow is the total flow at `target/sink`

### Properties of flow

- capacity constraint : 0 <= edge `flow` <= edge `capacity`
- local equilibrium at the vertices -> flow in= flow out

## What is the Max Flow problem?

- We want to `maximize` the `value of flow` at the `sink/target`

> These two problems are `dual`-> if we solve one, other also gets solved



# Min Cut Max Flow theorem

> ## The net flow across `any cut` will be the same 

Local equilibrium at each vertex ensures that no flow is lost..hence, the net flow will be the same

- Max Flow Min cut theorem is based on another theorems

> ## Augmenting Path theorem
>
> a flow is a max flow, if there are no augmenting paths are left to be considered

> ## Max Flow Min Cut theorem
>
> - `Value` of max flow = `Capacity` of min cut

We prove this lemma by 3 factors

- there exists a cut whose `capacity` = `value` of the flow
- now value of any other cut, will be less than equal to the capacity always -> thus this value is the max flow
- If there are augmenting paths left, they could increase the flow by sending some water...so in that case, then flow can be more(not max flow)...thus if no augmenting paths left, max flow 

# Finding Min Cut from max flow

Once we got the max flow, there are no augmenting paths left

To get the min cut, we follow those `forward edges that are not full` and `backward edge that is not empty` and identify the vertices in the path